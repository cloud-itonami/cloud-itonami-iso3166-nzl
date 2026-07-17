(ns statute.facts
  "General-law compliance catalog for New Zealand (NZL) -- a 46th
  country-level entry (see cloud-itonami-iso3166-jpn/-usa/-gbr/-deu/-fra/
  -can/-aus/-kor/-nld/-ita/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/
  -chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/-tha/
  -are/-vnm/-idn/-phl/-egy/-tur/-nga/-sau/-mys/-aut/-che/-irl for the
  first forty-five) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital-status finding
  from cloud-itonami-municipality-nzl-wellington (tick 134):
  Wellington is New Zealand's stable capital, with no ongoing
  ambiguity.

  New Zealand's official legislation portal (legislation.govt.nz) and
  two legal-database mirrors (austlii.edu.au, nzlii.org) all returned
  HTTP 403 on every URL tried, so this catalog's entries instead cite
  en.wikipedia.org, directly WebFetched and read (not merely
  WebSearch-cited).

  Companies Act 1993 -- Royal Assent date (28 September 1993) directly
  confirmed via en.wikipedia.org's own infobox. A specific Act number
  (commonly cited elsewhere, and inferable from the legislation.govt.nz
  URL structure, as '1993 No 105') could NOT be directly confirmed in
  the article's readable text itself -- only the URL pattern suggests
  it -- so :law-number uses only the directly-confirmed citation title
  rather than an inferred number, matching this session's established
  discipline (e.g. Ireland's Data Protection Act 2018 at tick 132).

  Privacy Act 2020 -- directly confirmed via en.wikipedia.org's own
  infobox: Royal Assent 30 June 2020, came into force 1 December 2020
  (used as :enacted-date, the date the Act actually took full legal
  effect, matching this session's established pattern).

  An entry not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "ISO3166 alpha-3 -> vector of statute entries."
  {"NZL"
   [{:statute/id "nzl.companies-act-1993"
     :statute/title "Companies Act 1993"
     :statute/jurisdiction "NZL"
     :statute/kind :law
     :statute/law-number "Companies Act 1993"
     :statute/url "https://en.wikipedia.org/wiki/Companies_Act_1993"
     :statute/url-provenance :wikipedia-corroborated
     :statute/enacted-date "1993-09-28"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "nzl.privacy-act-2020"
     :statute/title "Privacy Act 2020"
     :statute/jurisdiction "NZL"
     :statute/kind :law
     :statute/law-number "Privacy Act 2020"
     :statute/url "https://en.wikipedia.org/wiki/Privacy_Act_2020"
     :statute/url-provenance :wikipedia-corroborated
     :statute/enacted-date "2020-12-01"
     :statute/retrieved-at "2026-07-17"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [jurisdiction] (get catalog jurisdiction))

(defn coverage
  ([] (coverage (keys catalog)))
  ([jurisdictions]
   (let [have (filter catalog jurisdictions)
         missing (remove catalog jurisdictions)]
     {:requested (count jurisdictions)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-nzl statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "NZL")) " New Zealand entries seeded "
                 "with Wikipedia citations (legislation.govt.nz/austlii.edu.au/nzlii.org all 403'd). "
                 "Extend `statute.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [jurisdiction topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis jurisdiction)))
