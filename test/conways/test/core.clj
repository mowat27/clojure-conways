(ns conways.test.core
  (:use [conways.core])
  (:use [clojure.test]))

; Finding immediate neighbours
(deftest test-immediate-neighbours-for-pos-6 ;; FIXME: write
  (is (= [1 2 3 5 7 9 10 11] (immedaiate-neighbours 6 4)) "neighbours for pos 6, x size 4"))

(deftest test-immediate-neighbours-for-pos-5 ;; FIXME: write
  (is (= [0 1 2 4 6 8 9 10] (immedaiate-neighbours 5 4)) "neighbours for pos 5, x size 4"))

(deftest test-values-at
	(is (= [0 1 0] (values-at [0 1 2] [0 1 0 1 0]))))

(deftest test-values-at-2
	(is (= [1 0 1] (values-at [1 2 3] [0 1 0 1 0]))))

(deftest test-values-at-bad-index-is-zero
	(is (= [1 0 0] (values-at [1 2 -1] [0 1 0 1 0]))))


(deftest test-count-live-neighbours
	(is (= 0 (count-live-neighbours 6 [0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0] 4))))	

(deftest test-count-live-neighbours-2
	(is (= 1 (count-live-neighbours 6 [0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0] 4))))	

; Applying Conway's rules to a cell
(deftest test-next-value-live-cell-with-1-live-neighbour
	(is (= 0 (next-value 1 1)) 
		"live cell with 1 live neighbour dies"))
	
(deftest test-next-value-live-cell-with-2-live-neighbours
	(is (= 1 (next-value 2 1)) 
		"live cell with 2 live neighbours lives"))	

(deftest test-next-value-live-cell-with-3-live-neighbours
	(is (= 1 (next-value 3 1)) 
		"live cell with 3 live neighbours lives"))	

(deftest test-next-value-live-cell-with-4-live-neighbours
	(is (= 0 (next-value 4 1)) 
		"live cell with 4 live neighbours dies"))			

(deftest test-next-value-dead-cell-with-3-live-neighbours
	(is (= 1 (next-value 3 0)) 
		"dead cell with 3 live neighbour lives"))	

(deftest test-next-value-dead-cell-with-2-live-neighbours
	(is (= 0 (next-value 2 0)) 
		"dead cell with 2 live neighbours stays dead"))	

(deftest test-next-value-dead-cell-with-4-live-neighbours
	(is (= 0 (next-value 4 0)) 
		"dead cell with 4 live neighbours stays dead"))	

; Running the game
(deftest test-applying-rules-to-grid
	(is (= [0 0 0 0] (next-generation [0 1 0 0] 2))))

(deftest test-newlines
	(is (= ["" "\n"] (take 2 (newlines 2)))))	

(deftest test-translate-grid
	(is (= [" " "X\n"] (translate-grid [0 1] 2))))

(def grid "  X \n    \n")

(deftest test-printing-grid
	(is (= grid (print-grid [0 0 1 0 0 0 0 0] 4))))	



