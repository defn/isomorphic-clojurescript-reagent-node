(ns game.page
  (:require [reagent.core :as reagent]
            [secretary.core :as secretary :refer-macros [defroute]]
            [game.core :as game]))

(enable-console-print!)

(defn template [{:keys [body]}]
  [:html
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:link {:href "static/css/site.css" :rel "stylesheet" :type "text/css"}]]
   [:body
    [:div#app [body]]
    [:script {:type "text/javascript" :src "js/bundle.js"}]
    [:script {:type "text/javascript" :src "js/client/goog/base.js"}]
    [:script {:type "text/javascript" :src "js/client/client.js"}]
    [:script {:type "text/javascript"
              :dangerouslySetInnerHTML {:__html "goog.require('game.client');"}}]]])

(defn ^:export render-page [path]
  (reagent/render-to-static-markup 
    (do
      (secretary/dispatch! path)
      [template {:body game/app-view}])))
