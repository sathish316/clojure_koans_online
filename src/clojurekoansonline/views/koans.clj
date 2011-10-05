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

(defpage "/koan" {:keys [name]}
  (html5
   (include-css "./css/koan.css" "./css/button.css")
   (include-js "./js/ace/ace.js" "./js/ace/mode-clojure.js" "./js/ace/theme-twilight.js" "./js/ace/keybinding-vim.js" "./js/koan.js")
   [:h1 name
    [:button {:class "cupid-green run-koan"} "Run Koan"]]
   [:div {:id "editor"} (slurp (koan-path name))]
  ))
