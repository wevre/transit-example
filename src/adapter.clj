(ns adapter
  (:require [cognitect.transit :as transit]
            [ring.adapter.jetty :as jetty])
  (:import (java.io ByteArrayOutputStream)))

(defn handler [request]
  ;; decode body of request as transit data,
  ;; add the two numbers and return them.
  (let [reader (transit/reader (:body request) :json)
        {:keys [num1 num2]} (transit/read reader)]

    {:status 200
     :headers {"Content-Type" "application/transit+json"}
     :body (let [baos (ByteArrayOutputStream.)
                 writer (transit/writer baos :json)]
             (transit/write writer {:result (format "%.1f+%.1f=%.1f" num1 num2 (+ num1 num2))})
             (.toByteArray baos))}))

(defonce adapter (atom nil))

(defn go []
  (reset! adapter (jetty/run-jetty handler {:port 8860 :join? false})))

(defn halt! []
  (.stop @adapter)
  (reset! adapter nil))

(comment
  (go)
  (halt!)
  )