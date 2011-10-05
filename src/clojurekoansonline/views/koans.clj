(ns clojurekoansonline.views.koans
  (:require [clojurekoansonline.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(use '[clojure.contrib.duck-streams :only (read-lines)])

(def koan-folder (clojure.java.io/file "./koans"))

(def koan-names (map #(.getName %) (rest (file-seq koan-folder))))

(defn koan-path [koan-name]
     (str "./koans/" koan-name))

(defpartial goto-koan [koan]
  [:li
   [:a {:href (str "./koan?name=" koan)} koan]])

(defpartial koan-list [koans]
  [:ul#koans
   (map goto-koan koans)])

(defpage "/koans" []
  (html5
   [:h1 "Koans"]
   (koan-list koan-names)))

(defpartial line-of-code [line]
  [:div {:class "code"} line]
  )

(defpage "/koan" {:keys [name]}
  (html5
   [:h1 name]
   (map line-of-code (read-lines (koan-path name)))
  ))
