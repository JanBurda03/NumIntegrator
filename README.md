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
cd NumIntegrator
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

The output is the approximated value of the integral.
