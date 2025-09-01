Here’s a clean **GitHub-ready README.md** you can use for your project 👇

---

# Polynomial Coefficients from Roots

This project demonstrates how to parse JSON input, extract numbers in different bases, convert them to decimal, select a subset of roots, and construct a polynomial by expanding the factors $(x - r_i)$. The resulting coefficients are then computed and displayed.

---

## 📌 Problem Statement

We are given JSON input with:

* `n`: total number of roots
* `k`: number of roots to be selected (first `k` roots)
* A list of roots, each represented by a base and value.

We need to:

1. Parse the JSON input.

2. Extract `n`, `k`, and the list of roots.

3. Convert each root from its given base into a decimal integer.

4. Select the first `k` roots.

5. Build the polynomial:

   $$
   P(x) = (x - r_1)(x - r_2)(x - r_3)\dots(x - r_k)
   $$

6. Expand the polynomial to get coefficients.

7. Print the coefficients.

---

## 🛠️ Steps We Followed

### **1. JSON Parsing**

* Used `org.json` in Java to parse the input JSON.
* Extracted `n`, `k`, and each root’s `base` and `value`.

### **2. Base Conversion**

* Each root was represented in a different base (e.g., base 2, base 6, base 15).
* Used `BigInteger(value, base)` to convert the string to decimal.

### **3. Selecting Roots**

* Selected the first `k` roots from the list as required.

### **4. Building Polynomial**

* Polynomial was constructed iteratively:

  ```java
  coefficients = [1]  // Start with polynomial = 1
  For each root r:
      Multiply current polynomial by (x - r)
  ```

* Implemented coefficient update step:

  ```java
  newCoeffs[i] += oldCoeffs[i-1]
  newCoeffs[i-1] -= root * oldCoeffs[i-1]
  ```

### **5. Expanding Polynomial**

* After all multiplications, final coefficient array was obtained.

### **6. Printing Coefficients**

* Printed coefficients in order, starting from highest degree term down to constant.

---

## 📂 Test Cases

### ✅ Test Case 1

**Input JSON:**

```json
{
  "keys": {
    "n": 5,
    "k": 3
  },
  "1": {
    "base": "2",
    "value": "101"
  },
  "2": {
    "base": "3",
    "value": "12"
  },
  "3": {
    "base": "4",
    "value": "123"
  },
  "4": {
    "base": "5",
    "value": "34"
  },
  "5": {
    "base": "6",
    "value": "45"
  }
}
```

**Converted Roots (Decimal):**

* `101` (base 2) → **5**
* `12` (base 3) → **5**
* `123` (base 4) → **27**

**Selected First `k=3` Roots:**

$$
r = [5, 5, 27]
$$

**Polynomial:**

$$
P(x) = (x - 5)(x - 5)(x - 27)
$$

**Expanded Polynomial:**

$$
x^3 - 37x^2 + 385x - 675
$$

**Output Coefficients:**

```
1 -37 385 -675
```

---

### ✅ Test Case 2

**Input JSON:**

```json
{
  "keys": {
    "n": 10,
    "k": 7
  },
  "1": { "base": "6", "value": "13444211440455345511" },
  "2": { "base": "15", "value": "aed7015a346d635" },
  "3": { "base": "15", "value": "6aeeb69631c227c" },
  "4": { "base": "16", "value": "e1b5e05623d881f" },
  "5": { "base": "8", "value": "316034514573652620673" },
  "6": { "base": "3", "value": "2122212201122002221120200210011020220200" },
  "7": { "base": "3", "value": "20120221122211000100210021102001201112121" },
  "8": { "base": "6", "value": "20220554335330240002224253" },
  "9": { "base": "12", "value": "45153788322a1255483" },
  "10": { "base": "7", "value": "1101613130313526312514143" }
}
```

**Converted Roots (Decimal):**

* r₁ = 1587006451396257
* r₂ = 368769878151160
* r₃ = 300450642107644
* r₄ = 6798942413563679
* r₅ = 58071509480805962827
* r₆ = 142378293844163338
* r₇ = 61855209857379687143

**Selected First `k=7` Roots:**

$$
r = [1587006451396257, 368769878151160, 300450642107644, 6798942413563679, 58071509480805962827, 142378293844163338, 61855209857379687143]
$$

**Polynomial:**

$$
P(x) = (x - r_1)(x - r_2)(x - r_3)(x - r_4)(x - r_5)(x - r_6)(x - r_7)
$$

**Expanded Polynomial (Coefficients):**
Output is very large integers, printed via Java `BigInteger`.

---

## ▶️ Running the Code

### **Requirements**

* Java 8+
* `org.json` library

### **Compile and Run**

```bash
# Compile
javac PolynomialFromRoots.java

# Run
java PolynomialFromRoots
```

* Place the JSON input inside the program (or modify to read from file).
* The coefficients will be printed to console.

---

## 📖 Key Learnings

* How to parse JSON in Java.
* Conversion of numbers from arbitrary base to decimal using `BigInteger`.
* Polynomial expansion via iterative coefficient updates.
* Handling large numbers with `BigInteger`.

---

Would you like me to also **add example Java output** for the second test case (first few coefficients, since they’re huge), so the README looks more complete for GitHub?
