(ns conways.core)

(defn immedaiate-neighbours [pos x-size]
	(let [above (- pos x-size) below (+ pos x-size)]
	[(- above 1) above (+ above 1)
	 (- pos 1)         (+ pos 1)
	 (- below 1) below (+ below 1)]))

(defn values-at [posns source]
	(map #(get source %1) posns))

(defn count-live-neighbours [pos grid x-size]
	(reduce + (values-at (immedaiate-neighbours pos x-size) grid)))

(defn next-value [live-neighbours curr-state]
	(if (= curr-state 1)
		(let [will-live {2 1 3 1}]
			(get will-live live-neighbours 0))
		(if (= 3 live-neighbours) 1 0)))
