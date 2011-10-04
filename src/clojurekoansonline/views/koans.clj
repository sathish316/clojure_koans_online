(ns clojurekoansonline.views.koans
  (:require [clojurekoansonline.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(def koan-folder (clojure.java.io/file "/Users/sathish/code/clojure/clojurekoansonline/koans"))

(def koan-names (map #(.getName %) (file-seq koan-folder)))

(defpartial goto-koan [koan]
  [:li koan])

(defpartial koan-list [koans]
  [:ul#koans
   (map goto-koan koans)])

(defpage "/koans" []
  (html5
   [:h1 "Koans"]
   (koan-list koan-names)))


