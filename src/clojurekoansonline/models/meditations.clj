(ns clojurekoansonline.models.meditations)

(def __ :fill-in-the-blank)
(def ___ (fn [& args] __))

(defmacro fancy-assert
  ([x] (fancy-assert x ""))
  ([x message]
   `(try
     (assert ~x ~message)
     (catch Exception e#
       (throw (Exception. (str '~message "\n" '~x )
                          e#))))))

(defmacro meditations [& forms]
  (let [pairs (partition 2 forms)
        tests (map (fn [[doc# code#]]
                     `(fancy-assert ~code# ~doc#))
                   pairs)]
    `(do ~@tests)))
