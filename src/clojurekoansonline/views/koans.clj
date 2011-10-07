(ns clojurekoansonline.views.koans
  (:require [clojurekoansonline.views.common :as common]
            [noir.response :as response])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

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

(defn next-koan [koan-name]
  (koan-names (inc (.indexOf koan-names koan-name))))

(defn koan-path [koan-name]
  (str "./koans/" koan-name ".clj"))

(defn koan-url [koan]
  (str "./koan?name=" koan))

(defpartial goto-koan [koan]
  [:li
   [:a {:href (koan-url koan)} koan]])

(defpartial koan-list [koans]
  [:ol#koans
   (map goto-koan koans)])

(defpartial original-clojure-koans []
  [:div "Based on the original "
   [:a {:href "https://github.com/functional-koans/clojure-koans"} "Functional Koans/Clojure Koans"]])

(defpartial clojure-logo []
  [:img {:class "logo" :src "http://clojure.org/file/view/clojure-icon.gif"}])

(defpage "/" []
  (html5
   (include-css "./css/koan.css")
   (include-js "./js/jquery.min.js" "./js/ga.js")
   [:h1 "Clojure Koans"]
   (original-clojure-koans)
   (koan-list koan-names)))

(defpage "/koan" {:keys [name]}
  (html5
   (include-css "./css/koan.css" "./css/button.css")
   (include-js "./js/ace/ace.js" "./js/ace/mode-clojure.js" "./js/ace/theme-twilight.js" "./js/jquery.min.js" "./js/koan.js" "./js/ga.js")
   [:h1 name
    [:button {:class "cupid-green run-koan"} "Run Koan"]
    [:span#status ""]]
   [:a#next_koan {:class "hidden" :href (koan-url (next-koan name))} (str "Go to Next koan > " (next-koan name))]
   (original-clojure-koans)
   [:div#editor (slurp (koan-path name))]

  ))

(defpage [:post "/eval"] {:keys [code]}
  (use 'clojurekoansonline.models.meditations)
  (try
   (load-string code)
   (response/json {:status "PASS"})
   (catch Exception e
     (response/json {:status "FAIL" :message (.getMessage e)})))
  )