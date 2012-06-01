(ns localtruth.test.core
  (:use [localtruth.core])
  (:use [clojure.test]))

(deftest test-above-lower
  (is (above-lower :unbounded 0))
  (is (above-lower [:open -100] 0))
  (is (not (above-lower [:open 1] 0)))
  (is (not (above-lower [:open 0] 0)))
  (is (above-lower [:closed 0] 1))
  (is (above-lower [:closed 0] 0))
  (is (not (above-lower [:closed 1] 0))))

(deftest test-below-upper
  (is (below-upper :unbounded 0))
  (is (below-upper [:open 1] 0))
  (is (not (below-upper [:open 0] 0)))
  (is (not (below-upper [:open 0] 1)))
  (is (below-upper [:closed 1] 0))
  (is (below-upper [:closed 0] 0))
  (is (not (below-upper [:closed 0] 1))))

(deftest test-interval-contains
  (let [open-interval [[:open -100] [:open 100]]
        closed-interval [[:closed -100] [:closed 100]]
        unbounded-interval [:unbounded :unbounded]]
    (is (interval-contains open-interval 0))
    (is (interval-contains open-interval 99))
    (is (interval-contains open-interval -99))
    (is (not (interval-contains open-interval 100)))
    (is (not (interval-contains open-interval -100)))
    (is (interval-contains closed-interval 0))
    (is (interval-contains closed-interval 100))
    (is (interval-contains closed-interval -100))
    (is (not (interval-contains closed-interval 101)))
    (is (not (interval-contains closed-interval -101)))
    (is (interval-contains unbounded-interval -1000000))
    (is (interval-contains unbounded-interval 1000000))))

