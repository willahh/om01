(defproject om01 "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  
  :min-lein-version "2.7.1"
  
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [cljsjs/react "16.4.1-0"]
                 [cljsjs/react-dom "16.4.1-0"]
                 [cljsjs/react-dom-server "16.5.2-0"]
                 [cljsjs/create-react-class "15.6.3-1"]
                 [sablono "0.8.4"]
                 [org.omcljs/om "1.0.0-beta4"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.1"]
                 [secretary "1.2.3"]
                 [soupup "0.2.0"]
                 [clj-webdriver "0.7.2"]
                 [com.github.detro.ghostdriver/phantomjsdriver "1.0.3"]
                 [venantius/accountant "0.2.4"
                  :exclusions [org.clojure/tools.reader]]
                 [cljs-ajax "0.7.4"]]
  
  :source-paths ["src"]
  
  :ring {:handler om01.route/app}
  
  :plugins [[lein-ring "0.7.3"]]
  
  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" om01.test-runner]}
  
  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.1.9"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]]
                   :resource-paths ["target"]
                   ;; need to add the compliled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})

