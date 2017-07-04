(ns cot-tool.all-tests
  (:require [cot-tool.core-test]
            [taoensso.timbre :as timbre]))

(timbre/set-level! :debug)
