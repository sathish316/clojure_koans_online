(ns clojurekoansonline.views.koans
  (:require [clojurekoansonline.views.common :as common]
            [noir.content.pages :as pages]
            [noir.response :as response])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))
        

(def koan-folder (clojure.java.io/file "./koans"))

(def koan-names
     ["equalities"
      "lists"
      "vectors"
      "sets"
      "maps"
      "functions"
      "conditionals"
      "higher_order_functions"
      "runtime_polymorphism"
      "lazy_sequences"
      "sequence_comprehensions"
      "creating_functions"
      "recursion"
      "destructuring"
      "refs"
      "atoms"
      "macros"
      "datatypes"
      "java_interop"])

(defn koan-path [koan-name]
     (str "./koans/" koan-name ".clj"))

(defpartial goto-koan [koan]
  [:li
   [:a {:href (str "./koan?name=" koan)} koan]])

(defpartial koan-list [koans]
  [:ol#koans
   (map goto-koan koans)])

(defpage "/koans" []
  (html5
   (include-css "./css/koan.css")
   [:h1 [:img {:class "logo" :src "http://clojure.org/file/view/clojure-icon.gif"}] "Clojure Koans"]
   (koan-list koan-names)))

(defpage "/koan" {:keys [name]}
  (html5
   (include-css "./css/koan.css" "./css/button.css")
   (include-js "./js/ace/ace.js" "./js/ace/mode-clojure.js" "./js/ace/theme-twilight.js" "./js/jquery.min.js" "./js/koan.js")
   [:h1 name
    [:button {:class "cupid-green run-koan"} "Run Koan"]
    [:span {:id "status"} ""]]
   [:div {:id "editor"} (slurp (koan-path name))]

  ))

(defpage [:post "/eval"] {:keys [code]}
  (use 'clojurekoansonline.models.meditations)
  (try
   (load-string code)
   (response/json {:status "PASS"})
   (catch Exception e
     (response/json {:status "FAIL" :message (.getMessage e)})))
  )