(ns conways.core)

(defn immedaiate-neighbours [pos x-size]
	(let [above (- pos x-size) below (+ pos x-size)]
	[(- above 1) above (+ above 1)
	 (- pos 1)         (+ pos 1)
	 (- below 1) below (+ below 1)]))

(defn values-at [posns source]
	(map #(get source %1 0) posns))

(defn count-live-neighbours [pos grid x-size]
	(reduce + (values-at (immedaiate-neighbours pos x-size) grid)))

(defn next-value [live-neighbours curr-state]
	(if (= curr-state 1)
		(let [will-live {2 1 3 1}]
			(get will-live live-neighbours 0))
		(if (= 3 live-neighbours) 1 0)))

(def zero-indexed-seq (iterate inc 0))
(def one-indexed-seq (iterate inc 1))

(defn next-generation [grid x-size] 
	(into [] (map 
		(fn [pos curr-state] 
			(next-value (count-live-neighbours pos grid x-size) curr-state))
		zero-indexed-seq grid)))

(defn newlines [x-size] 
	(map #(if (= 0 (mod %1 x-size)) "\n" "") one-indexed-seq))		

(defn translate-grid [grid x-size] 
	(map #(str (if (= 1 %1) "X" " ") %2) grid (newlines x-size)))		

(defn print-grid [grid x-size]
	(reduce str "" (translate-grid grid x-size)))

(def start
	[0 0 0 0 0 0 0 0
	 0 0 1 1 1 0 0 0
	 0 1 1 1 0 0 0 0
	 0 0 0 0 0 0 0 0
	 0 0 0 0 0 1 0 0
	 0 0 0 0 0 1 0 0
	 0 0 0 0 0 1 0 0
	 0 0 0 0 0 0 0 0
	])	


(defn live-cells [grid] (reduce + grid))

(defn do-game [grid x-size]
	(println (print-grid grid x-size))
	(Thread/sleep 250)
	(let [next-gen (next-generation grid x-size)]
		(if (not (zero? (live-cells next-gen))) 
			(recur next-gen x-size))))

