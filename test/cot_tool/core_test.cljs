(ns cot-tool.core-test
  (:require [cljs.test     :refer-macros [is]]
            [devcards.core :refer-macros [defcard-rg deftest]]
            [cot-tool.core :as core]
            [reagent.core  :as r]))

(defcard-rg race-select
  [core/race-select])

(defcard-rg attribute-values
  [core/attribute-values])

(defcard-rg life
  [core/life])

(deftest calc-skill-point
  (is (= 0 (core/calc-skill-points 0)))
  (is (= 1 (core/calc-skill-points 1)))
  (is (= 2 (core/calc-skill-points 2)))
  (is (= 3 (core/calc-skill-points 3)))
  (is (= 5 (core/calc-skill-points 4)))
  (is (= 7 (core/calc-skill-points 5)))
  (is (= 9 (core/calc-skill-points 6)))
  (is (= 12 (core/calc-skill-points 7))))

(deftest used-skill-points
  (is (= 0 (core/used-skill-points {})))
  (is (= 2 (core/used-skill-points {:a 1
                                    :b 1})))
  (is (= 10 (core/used-skill-points {:a 4
                                     :b 4})))
  (is (= 17 (core/used-skill-points {:a 4
                                     :b 4
                                     :c 5}))))

(defcard-rg state
  [core/current-state])

(defcard-rg sample-component
  [core/app])
