(ns clojurekoansonline.views.welcome
  (:require [clojurekoansonline.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/welcome" []
  (html5
   [:h1 "Welcome to Clojure Koans"]))
