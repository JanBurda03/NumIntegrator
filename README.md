# NumIntegrator

`NumIntegrator` is a tool for approximating **multidimensional integrals** using sampling methods.

---

## Overview

NumIntegrator approximates multidimensional integrals using sampling-based methods.
A fixed number of sample points is generated within the integration domain according
to the selected sampling strategy. The function is evaluated at each sample point and
the results are averaged. The final integral value is obtained by multiplying this
average by the volume of the integration domain, which is computed as the product of
(max − min) over all variable ranges.

Supports three sampling strategies:

- **GRID** – uniform grid of evenly spaced sample points
- **RANDOM** – independent random points
- **HALTON** – quasi-random low-discrepancy sequence for better coverage than pure random points

---

## Installation & Running

1. **Clone the repository:**

```bash
git clone https://github.com/JanBurda03/NumIntegrator.git
```


2. **Build the project using Maven:**

```bash
mvn clean package
```

3. **Run the integrator with four arguments:**

<expression> <sampleCount> <samplingType> <variables>

- `<expression>` – function to integrate, e.g., `x*y`
- `<sampleCount>` – total number of samples, e.g., `1000`
- `<samplingType>` – `GRID`, `RANDOM`, or `HALTON`
- `<variables>` – variable ranges, e.g., `x 0 1 y 0 1`

**Example:**

```bash
java -cp target/NumIntegrator.jar cz.janburda03.numintegrator.app.Main x+y 1000 GRID x 0 1 y 0 1
```

```bash
0.9849032258064516
```

---

## Example: More Complex Expression

```bash
java -cp target/NumIntegrator.jar cz.janburda03.numintegrator.app.Main \
"sqrt(abs(x1)) + sin(x2+pi) - cos(x3-pi) * tan(x1) / (cot(x2) + ln(pi) + log(2*e)) + exp(x3) + pi^x1" \
10000 HALTON \
x1 0 1 x2 -1 1 x3 -10 10
```

```bash
44139.830175395175
```

```bash
java -cp target/NumIntegrator.jar cz.janburda03.numintegrator.app.Main \
"sqrt(abs(x1)) + sin(x2+pi) - cos(x3-pi) * tan(x1) / (cot(x2) + ln(pi) + log(2*e)) + exp(x3) + pi^x1" \
10000 RANDOM \
x1 0 1 x2 -1 1 x3 -10 10
```

```bash
44735.643493311734
```

```bash
java -cp target/NumIntegrator.jar cz.janburda03.numintegrator.app.Main \
"sqrt(abs(x1)) + sin(x2+pi) - cos(x3-pi) * tan(x1) / (cot(x2) + ln(pi) + log(2*e)) + exp(x3) + pi^x1" \
1000000 GRID \
x1 0 1 x2 -1 1 x3 -10 10
```

```bash
48266.531694641024
```

---

## Supported Expressions

### Variables

- Names consist of letters and digits (e.g. `x`, `x1`, `var2`)
- Every variable used in the expression **must have a specified range**

### Constants

| Constant | Value |
|----------|-------|
| `pi`     | π     |
| `e`      | Euler's number |

### Binary Operators

| Operator | Description |
|----------|------------|
| `+`      | addition |
| `-`      | subtraction |
| `*`      | multiplication |
| `/`      | division |
| `^`      | power |

### Unary Operators

- Unary minus: `-x`

### Functions

| Function | Description       |
|----------|-------------------|
| `sin(x)` | sine              |
| `cos(x)` | cosine            |
| `tan(x)` | tangent           |
| `cot(x)` | cotangent         |
| `ln(x)`  | natural logarithm |
| `log(x)` | base-10 logarithm |
| `exp(x)` | exponential       |
| `abs(x)` | absolute value    |
| `sqrt(x)`| square root       |

### Parentheses

- Fully supported: `( )`
- Can be nested arbitrarily, e.g. `sqrt((x + y)/2)`

---

The program prints **a single number** — the approximated value of the integral over the
specified multidimensional domain.
