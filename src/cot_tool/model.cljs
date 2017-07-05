(ns cot-tool.model)

(def race-names
  {:human "Mensch"
   :elf   "Elf"
   :dwarf "Zwerg"
   :lizard "Echsenwesen"
   :gnom "Gnom"
   :animal-human "Tiermensch"})

(def attributes [:strength :constitution :dexterity
  :intelligence :wisdom :charisma])

(def attribute-names
  {:strength "St"
   :constitution "Ko"
   :dexterity "Ge"
   :intelligence "In"
   :wisdom "We"
   :charisma "Ch"})

(def skill-list
  [[:a "Angeln"]
   [:b "Athletik"]
   [:c "Bluffen/Diplomatie/Einschüchtern"]
   [:d "Brauen"]
   [:e "Entdecken/Lauschen"]
   [:f "Fallenstellen"]
   [:g1 "Handwerk:"]
   [:g2 "Handwerk:"]
   [:g3 "Handwerk:"]
   [:g4 "Handwerk:"]
   [:h "Heilkunde"]
   [:i "Infos sammeln"]
   [:j "Klettern"]
   [:k "Kochen"]
   [:l "Körperbeherrschung"]
   [:m "Mechanismen ausschalten"]
   [:n "Menschenkenntnis"]
   [:o "Mit Tieren umgehen"]
   [:p "Orientierung"]
   [:q "Reiten"]
   [:r "Schleichen"]
   [:s "Schlösser knacken"]
   [:t "Schwimmen"]
   [:u "Schätzen"]
   [:v "Selbstbeherrschung"]
   [:w "Spuren lesen"]
   [:x "Taschendiebstahl"]
   [:y "Unterhaltungskunst"]
   [:z "Überlebenskunst"]
   [:aa1 "Wissen: "]
   [:aa2 "Wissen: "]
   [:aa3 "Wissen: "]
   [:aa4 "Wissen: "]
   [:ab "Zauberkunde"]])

(def skills
  (map first skill-list))

(def skill-names
  (into {} skill-list))
