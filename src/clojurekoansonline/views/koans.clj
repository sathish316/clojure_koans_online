(ns clojurekoansonline.views.koans
  (:require [clojurekoansonline.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/koans" []
  (html5
   [:h1 "Koans"]))