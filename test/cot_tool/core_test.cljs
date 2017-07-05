(ns cot-tool.core-test
  (:require [cljs.test     :refer-macros [is]]
            [devcards.core :refer-macros [defcard-rg deftest]]
            [cot-tool.core :as core]
            [reagent.core  :as r]))

(defcard-rg Name
  [core/name-class])

(defcard-rg race-select
  [core/race-select])

(defcard-rg attributes-values
  [core/attribute-values])

(defcard-rg life
  [core/life])

(defcard-rg skill-values
  [core/skill-values])

(defcard-rg state
  [core/current-state])

(defcard-rg used-skill-points ;wo kommt die 1 her?? und wie bekommm ich
  ;hier die rechnung der skillpunkte hin?
  [core/show-used-points])

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

(deftest attribute-modification
  (is (= 0 (core/attribute-modification 10 )))
  (is (= 1 (core/attribute-modification 12 )))
  (is (= 1 (core/attribute-modification 13 )))
  (is (= -1 (core/attribute-modification 9 )))
  (is (= -2 (core/attribute-modification 7 )))
  (is (= -2 (core/attribute-modification 6 ))))

(defcard-rg sample-component
  [core/app])
