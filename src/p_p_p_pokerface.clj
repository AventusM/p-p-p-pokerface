(ns p-p-p-pokerface)

(defn
  suit
  "Palauttaa kortin maan"
  [card]
  (let [[_ maa] card]
   (str maa))
  )

(suit "2H")
(suit "2D")
(suit "2C")
(suit "3S")

(def replacements {\T 10, \J 11, \Q 12, \K 13, \A 14})

(defn
  rank
  "Palauttaa kortin arvon väliltä 2-14"
  [card]
  (let [[arvo maa] card]
    (if (Character/isDigit arvo)
      (Integer/valueOf (str arvo))
      (replacements arvo)
      ))
  )

(rank "2H")
(rank "4S")
(rank "TS")
(rank "AS")

;CARDS
;CARDS

(def high-seven                   ["2H" "3S" "4C" "5C" "7D"])
(def pair-hand                    ["2H" "2S" "4C" "5C" "7D"])
(def two-pairs-hand               ["2H" "2S" "4C" "4D" "7D"])
(def three-of-a-kind-hand         ["2H" "2S" "2C" "4D" "7D"])
(def four-of-a-kind-hand          ["2H" "2S" "2C" "2D" "7D"])
(def straight-hand                ["2H" "3S" "6C" "5D" "4D"])
(def low-ace-straight-hand        ["2H" "3S" "4C" "5D" "AD"])
(def high-ace-straight-hand       ["TH" "AS" "QC" "KD" "JD"])
(def flush-hand                   ["2H" "4H" "5H" "9H" "7H"])
(def full-house-hand              ["2H" "5D" "2D" "2C" "5S"])
(def straight-flush-hand          ["2H" "3H" "6H" "5H" "4H"])
(def low-ace-straight-flush-hand  ["2D" "3D" "4D" "5D" "AD"])
(def high-ace-straight-flush-hand ["TS" "AS" "QS" "KS" "JS"])

;CARDS
;CARDS

;keys
(keys (frequencies [4 7 7 4 7]))
;values
(vals (frequencies [4 7 7 4 7]))

(defn
  pair?
  [hand]
  (< 1 (apply max (vals (frequencies (map rank hand)))))
  )

(pair? pair-hand)
(pair? high-seven)

(defn
  three-of-a-kind?
  [hand]
  (< 2 (apply max (vals (frequencies (map rank hand)))))
  )

(three-of-a-kind? two-pairs-hand)
(three-of-a-kind? three-of-a-kind-hand)

(defn
  four-of-a-kind?
  [hand]
  (< 3 (apply max (vals (frequencies (map rank hand)))))
  )

(four-of-a-kind? two-pairs-hand)
(four-of-a-kind? four-of-a-kind-hand)

(defn
  flush?
  [hand]
  (< 4 (apply max (vals (frequencies (map suit hand)))))
  )

(flush? pair-hand)
(flush? flush-hand)

(defn
  full-house?
  [hand]
  (= (seq [2 3]) (sort (vals (frequencies (map rank hand)))))
  )

;Tehtävän tarkoitus varmaankin tuo (seq vector-map-list) - rangesta ei hyötyä

(full-house? three-of-a-kind-hand)
(full-house? full-house-hand)

(defn
  two-pairs?
  [hand]
  (or (four-of-a-kind? hand) (= (seq [1 2 2]) (sort (vals (frequencies (map rank hand))))))
  )

(two-pairs? two-pairs-hand)
(two-pairs? pair-hand)
(two-pairs? four-of-a-kind-hand)

;Muutetaan - voi unohtaa värin (on jo metodi olemassa)
(defn
  low-ace-straight?
  [hand]
  (let
    [low-ace-sorted-hand (sort (replace {14 1} hand))]
    (= [1 2 3 4 5] low-ace-sorted-hand)
  ))

;KÄYTÄ INDEKSEINÄ
(replace {2 "a", 3 "b"} [1 2 3 4])
;KÄYTÄ INDEKSEINÄ

;HIGH-ACE
(defn
  straight?
  [hand]
  (let
    [lowest-hand-rank (first (sort (map rank hand)))
     sorted-hand (sort (map rank hand))
     card-range (range lowest-hand-rank (+ lowest-hand-rank 5))]

    (or (= card-range sorted-hand)
        (low-ace-straight? sorted-hand)))
    )

(straight? straight-hand)
(straight? high-ace-straight-hand)
(straight? low-ace-straight-hand)
(straight? two-pairs-hand)

(defn
  straight-flush?
  [hand]
  (and (straight? hand) (flush? hand))
  )

(straight-flush? straight-hand)
(straight-flush? straight-flush-hand)

(defn
  value
  [hand]

  )
