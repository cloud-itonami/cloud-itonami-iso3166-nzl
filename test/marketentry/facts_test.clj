(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest nzl-has-spec-basis
  (let [sb (facts/spec-basis "NZL")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "NZL")))
    (is (some? (facts/corporate-number-spec-basis "NZL")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "NZL")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "NZL" all)))
    (is (not (facts/required-evidence-satisfied? "NZL" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["NZL" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))
