(ns app.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(def colors
  (r/atom ["red" "black" "black" 
           "black" "yellow" "black"
           "black" "black" "green" 
           "black" "yellow" "black"
           ]))


(defn traffic-light []
  (fn []
    (js/setTimeout #(swap! colors (fn []
                                   (drop 3 (concat @colors (take 3 @colors))))) 1000)
    [:div.standing
     [:div [:p.circle {:style {:background (first @colors)}}]]
     [:div [:p.circle {:style {:background (second @colors)}}]]
     [:div [:p.circle {:style {:background (nth @colors 2)}}]]
    ]))

(defn ^:dev/after-load render
  "Render the toplevel component for this app."
  []
  (rdom/render [traffic-light]
               (.getElementById js/document "app")))

(defn main []
  (js/console.log "Yahoo!")
  (render))