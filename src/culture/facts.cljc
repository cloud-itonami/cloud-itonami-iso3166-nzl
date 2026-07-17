(ns culture.facts
  "Country-level regional-culture catalog for New Zealand (NZL) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"NZL"
   [{:culture/id "nzl.dish.hangi"
     :culture/name "Hangi"
     :culture/name-local "Hāngī"
     :culture/country "NZL"
     :culture/kind :dish
     :culture/summary "Traditional New Zealand Maori method of cooking food using heated rocks buried in a pit oven (umu), part of Aotearoa/New Zealand cultural identity."
     :culture/url "https://en.wikipedia.org/wiki/H%C4%81ng%C4%AB"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.dish.pavlova"
     :culture/name "Pavlova"
     :culture/country "NZL"
     :culture/kind :dish
     :culture/summary "Meringue-based dessert whose origin is a long-standing, unresolved dispute between New Zealand and Australia; the earliest known recipe for a 'pavlova cake' is documented in New Zealand in 1929."
     :culture/url "https://en.wikipedia.org/wiki/Pavlova"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.dish.hokey-pokey-ice-cream"
     :culture/name "Hokey Pokey ice cream"
     :culture/country "NZL"
     :culture/kind :dish
     :culture/summary "Ice-cream flavour in New Zealand and Australia consisting of plain vanilla ice cream with small, solid lumps of honeycomb toffee."
     :culture/url "https://en.wikipedia.org/wiki/Hokey_pokey_(ice_cream)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.beverage.lp"
     :culture/name "L&P"
     :culture/name-local "Lemon & Paeroa"
     :culture/country "NZL"
     :culture/kind :beverage
     :culture/summary "New Zealand soft drink combining lemon flavouring with carbonated mineral water, originating in the town of Paeroa."
     :culture/url "https://en.wikipedia.org/wiki/L%26P"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.product.manuka-honey"
     :culture/name "Manuka Honey"
     :culture/name-local "Mānuka honey"
     :culture/country "NZL"
     :culture/kind :product
     :culture/summary "Monofloral honey produced from the nectar of the manuka tree (Leptospermum scoparium), indigenous to New Zealand and parts of coastal Australia."
     :culture/url "https://en.wikipedia.org/wiki/M%C4%81nuka_honey"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.craft.pounamu-carving"
     :culture/name "Pounamu carving"
     :culture/country "NZL"
     :culture/kind :craft
     :culture/summary "Greenstone (jade/nephrite) carving that plays an important role in Maori culture, producing objects such as hei tiki pendants, mere weapons and fishing hooks."
     :culture/url "https://en.wikipedia.org/wiki/Pounamu"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.festival.matariki"
     :culture/name "Matariki"
     :culture/country "NZL"
     :culture/kind :festival
     :culture/summary "Maori celebration of the first rising of the Pleiades star cluster in late June or early July; became an official public holiday in New Zealand on 24 June 2022."
     :culture/url "https://en.wikipedia.org/wiki/Matariki"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "nzl.heritage.tongariro-national-park"
     :culture/name "Tongariro National Park"
     :culture/country "NZL"
     :culture/kind :heritage
     :culture/summary "New Zealand national park recognised by UNESCO as a World Heritage Site for both its natural values (1990) and its Maori cultural significance (dual heritage status, 1993)."
     :culture/url "https://en.wikipedia.org/wiki/Tongariro_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-nzl culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "NZL"))
                 " NZL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
