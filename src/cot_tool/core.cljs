(ns cot-tool.core
  (:require [cot-tool.model :as model]
            [cljs.pprint :refer [pprint]]
            [reagent.core :as reagent]))

(defn calc-skill-points [points]
  (if (zero? points)
    0
    (let [delta (quot (+ points 2) 3)]
      (+ delta (calc-skill-points (dec points))))))

(defn used-skill-points [skill-map]
  (reduce + (map calc-skill-points (vals skill-map))))

(defonce state
  (reagent/atom
   {:race :human
    :attr (zipmap model/attributes (repeat 8))
    :modi (zipmap model/attributes (repeat 0))
    :skill (zipmap model/skills (repeat 0))
    :bought-life 0}))

(defn race-select []
  [:select {:on-change (fn [event]
                         (let [race-string (.-value (.-target event))
                               race (keyword race-string)]
                           (swap! state assoc :race race)))
            :value (:race @state)}
   (for [[race race-name] model/race-names]
     ^{:key race}
     [:option {:value race}
      race-name])])

(defn name-class []
  [:div
  "Name "
  [:input ]
  " Klasse "
  [:input ]])

(defn attribute-value [att]
  [:tr
   [:td (model/attribute-names att)]
   [:td (get-in @state [:attr att])]
   [:td (get-in @state [:modi att])]
   [:td [:button {:on-click (fn []
                              (swap! state update-in [:attr att] inc) )}
         "+"]]
   [:td [:button {:on-click (fn []
                             (swap! state update-in [:attr att] dec) )}
         "-"]]])

(defn attribute-values []
  [:table
   [:tbody
    (for [att model/attributes]
      ^{:key att}
      [attribute-value att])]])

(defn attribute-modification [point]
  (let [x (quot point 2)]
    (- x 5)))

(defn add-attribute-modification [point]
  (+ point (attribute-modification point)))

(defn attribute-modifications [att]
  (swap! state update-in [:modi att] add-attribute-modification))

(defn life []
  (let [constitution (:constitution (:attr @state))
        bought-life (:bought-life @state)
        calculated-life (+ constitution bought-life)]
    [:div
     "LeP "
     calculated-life
     " (" bought-life ") "
     [:button {:on-click (fn []
                           (swap! state update :bought-life inc))}
      "+"]
     " "
     [:button {:on-click (fn []
                           (swap! state update :bought-life dec))}
            "-"]]))

(defn skill-value [sk]
  [:tr
   [:td (model/skill-names sk)]
   [:td (get-in @state [:skill sk])]
   [:td [:button {:on-click (fn []
                              (swap! state update-in [:skill sk] inc) )}
         "+"]]
   [:td [:button {:on-click (fn []
                              (swap! state update-in [:skill sk] dec) )}
         "-"]]])

(defn skill-values []
  [:table
   [:tbody
    (for [sk model/skills]
      ^{:key sk}
      [skill-value sk])]])

(defn current-state []
  [:pre (with-out-str (pprint @state))])

(defn show-used-points []
  [:pre (used-skill-points [])])

(defn app []
  [:h1 "Charakter erstellen"])

(defn ^:export run []
  (when-let [dom-node (.getElementById js/document "container")]
    (reagent/render app dom-node)))
