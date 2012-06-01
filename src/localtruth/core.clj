(ns localtruth.core)

(defn upper-bound [[lower upper]] upper)
(defn lower-bound [[lower upper]] lower)

(defn bound-type [bound]
   (if (= bound :unbounded) :unbounded (first bound)))

(defn dispatch-on-bound-type [bound value]
    (bound-type bound))

(defmulti above-lower dispatch-on-bound-type)
(defmethod above-lower :unbounded [bound value] true)
(defmethod above-lower :open [[type bound-value] value]
    (> value bound-value))
(defmethod above-lower :closed [[type bound-value] value]
    (>= value bound-value))

(defmulti below-upper dispatch-on-bound-type)
(defmethod below-upper :unbounded [bound value] true)
(defmethod below-upper :open [[type bound-value] value]
    (< value bound-value))
(defmethod below-upper :closed [[type bound-value] value]
    (<= value bound-value))

(defn interval-contains [interval value]
    (and (above-lower (lower-bound interval) value)
         (below-upper (upper-bound interval) value)))
