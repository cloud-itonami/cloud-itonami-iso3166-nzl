(ns marketentry.facts "New Zealand market-entry catalog.")
(def catalog
  {"NZL" {:name "New Zealand"
          :owner-authority "MBIE / GETS"
          :legal-basis "Government Procurement Rules"
          :national-spec "GETS supplier registration + NZBN/Companies Office"
          :provenance "https://www.gets.govt.nz/"
          :required-evidence ["NZBN/Companies Office record" "GETS registration record" "Companies Office extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / MBIE"
          :rep-legal-basis "New Zealand business registration typically required for government awards"
          :rep-provenance "https://www.gets.govt.nz/"
          :corporate-number-owner-authority "Companies Office / Inland Revenue"
          :corporate-number-legal-basis "NZBN / company number"
          :corporate-number-provenance "https://companies-register.companiesoffice.govt.nz/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "AUS" {:name "Australia" :owner-authority "AusTender" :legal-basis "CPRs" :national-spec "AusTender" :provenance "https://www.tenders.gov.au/"
          :required-evidence ["ABN record" "AusTender registration" "ASIC extract" "Authorized-representative record"]}
   "GBR" {:name "United Kingdom" :owner-authority "CCS" :legal-basis "PA 2023" :national-spec "Find a Tender" :provenance "https://www.find-tender.service.gov.uk/"
          :required-evidence ["Companies House number" "Find a Tender registration" "CH extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
