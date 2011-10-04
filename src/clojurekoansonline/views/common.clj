(ns clojurekoansonline.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpartial layout [& content]
  (html5
   [:head
    [:title "Clojure Koans Online"]]
   [:body
    [:div#wrapper
     content]]))