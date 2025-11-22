¿Qué es una Matriz Inversa?
La matriz inversa (denotada A⁻¹) de una matriz cuadrada A es aquella matriz que, al multiplicarse con A, produce la matriz identidad I. Es el equivalente matricial de la división o el recíproco en números reales.
Propiedad fundamental: A × A⁻¹ = A⁻¹ × A = I
Características Importantes:

Requisitos:

La matriz DEBE ser cuadrada (n × n)
El determinante debe ser diferente de cero (det(A) ≠ 0)
Si det(A) = 0, la matriz es singular y NO tiene inversa


Complejidad temporal: O(n³) - usando eliminación de Gauss-Jordan
Complejidad espacial: O(n²) - matriz aumentada
Aplicaciones:

Resolver sistemas de ecuaciones lineales (Ax = b → x = A⁻¹b)
Transformaciones geométricas inversas (gráficos por computadora)
Criptografía (cifrado de Hill)
Análisis de circuitos eléctricos
Regresión lineal y estadística



Conceptos clave:

Matriz identidad (I): Matriz con 1s en la diagonal y 0s en otros lugares
Matriz aumentada: Matriz original concatenada con la identidad [A | I]
Eliminación de Gauss-Jordan: Transformar [A | I] en [I | A⁻¹]


Funcionamiento del Código
Este programa calcula la matriz inversa usando el método de Gauss-Jordan:
1. Lectura de la matriz (readMatrixFromFile)

Lee números desde un archivo de texto (separados por espacios)
Cada línea representa una fila de la matriz
Construye una matriz bidimensional double[][]

2. Validación de matriz cuadrada (invertMatrix)
java   if (row.length != n)
       throw new IllegalArgumentException("La matriz no es cuadrada.");

Verifica que todas las filas tengan la misma longitud que el número de columnas

3. Construcción de matriz aumentada
java   double[][] aug = new double[n][2 * n];

Crea una matriz de n × 2n
Lado izquierdo: copia de la matriz original A
Lado derecho: matriz identidad I
Ejemplo: [A | I]

4. Algoritmo de Gauss-Jordan
Para cada fila i (pivote):
Paso A: Normalizar fila pivote
java   double p = aug[i][i];  // Elemento pivote
   for (int j = 0; j < 2 * n; j++)
       aug[i][j] /= p;    // Dividir toda la fila por el pivote

Convierte el elemento diagonal en 1

Paso B: Eliminación (hacer ceros en columna del pivote)
java   for (int k = 0; k < n; k++) {
       if (k != i) {
           double factor = aug[k][i];
           for (int j = 0; j < 2 * n; j++)
               aug[k][j] -= factor * aug[i][j];
       }
   }

Para cada fila k (excepto la pivote):

Resta un múltiplo de la fila pivote para hacer cero en aug[k][i]



Paso C: Verificación de singularidad
java   if (p == 0) throw new ArithmeticException("La matriz no tiene inversa.");

Si el pivote es cero, la matriz es singular

5. Extracción de la inversa
java   System.arraycopy(aug[i], n, inv[i], 0, n);
```
   - Después del proceso, la mitad derecha de la matriz aumentada contiene A⁻¹
   - Copia las columnas n hasta 2n-1 a la matriz resultado

### 6. **Escritura y visualización**
   - Escribe ambas matrices (original e inversa) en archivo
   - Muestra ambas matrices en consola

---

## Prueba de Escritorio (Ejemplo Manual)

### Datos de entrada:
**Archivo de entrada**: `matriz.txt`
```
2 1 1
1 3 2
1 0 0
```

**Matriz A (3×3)**:
```
┌         ┐
│ 2  1  1 │
│ 1  3  2 │
│ 1  0  0 │
└         ┘
```

---

### **PASO 1: Construir matriz aumentada [A | I]**
```
┌                     ┐
│ 2  1  1 | 1  0  0  │
│ 1  3  2 | 0  1  0  │
│ 1  0  0 | 0  0  1  │
└                     ┘
```

---

### **ITERACIÓN 1: Procesar fila 0 (pivote = 2)**

**Normalizar fila 0** (dividir por 2):
```
┌                           ┐
│ 1  0.5  0.5 | 0.5  0   0  │
│ 1   3    2  |  0   1   0  │
│ 1   0    0  |  0   0   1  │
└                           ┘
```

**Eliminar columna 0 en filas 1 y 2**:

Fila 1 = Fila 1 - (1 × Fila 0):
```
[1, 3, 2, 0, 1, 0] - 1×[1, 0.5, 0.5, 0.5, 0, 0]
= [0, 2.5, 1.5, -0.5, 1, 0]
```

Fila 2 = Fila 2 - (1 × Fila 0):
```
[1, 0, 0, 0, 0, 1] - 1×[1, 0.5, 0.5, 0.5, 0, 0]
= [0, -0.5, -0.5, -0.5, 0, 1]
```

**Resultado**:
```
┌                              ┐
│ 1   0.5   0.5  |  0.5   0  0 │
│ 0   2.5   1.5  | -0.5   1  0 │
│ 0  -0.5  -0.5  | -0.5   0  1 │
└                              ┘
```

---

### **ITERACIÓN 2: Procesar fila 1 (pivote = 2.5)**

**Normalizar fila 1** (dividir por 2.5):
```
┌                              ┐
│ 1   0.5   0.5  |  0.5   0   0 │
│ 0    1    0.6  | -0.2  0.4  0 │
│ 0  -0.5  -0.5  | -0.5   0   1 │
└                              ┘
```

**Eliminar columna 1 en filas 0 y 2**:

Fila 0 = Fila 0 - (0.5 × Fila 1):
```
[1, 0.5, 0.5, 0.5, 0, 0] - 0.5×[0, 1, 0.6, -0.2, 0.4, 0]
= [1, 0, 0.2, 0.6, -0.2, 0]
```

Fila 2 = Fila 2 - (-0.5 × Fila 1):
```
[0, -0.5, -0.5, -0.5, 0, 1] + 0.5×[0, 1, 0.6, -0.2, 0.4, 0]
= [0, 0, -0.2, -0.6, 0.2, 1]
```

**Resultado**:
```
┌                              ┐
│ 1   0   0.2  |  0.6  -0.2  0 │
│ 0   1   0.6  | -0.2   0.4  0 │
│ 0   0  -0.2  | -0.6   0.2  1 │
└                              ┘
```

---

### **ITERACIÓN 3: Procesar fila 2 (pivote = -0.2)**

**Normalizar fila 2** (dividir por -0.2):
```
┌                             ┐
│ 1  0  0.2 |  0.6  -0.2   0  │
│ 0  1  0.6 | -0.2   0.4   0  │
│ 0  0   1  |   3    -1   -5  │
└                             ┘
```

**Eliminar columna 2 en filas 0 y 1**:

Fila 0 = Fila 0 - (0.2 × Fila 2):
```
[1, 0, 0.2, 0.6, -0.2, 0] - 0.2×[0, 0, 1, 3, -1, -5]
= [1, 0, 0, 0, 0, 1]
```

Fila 1 = Fila 1 - (0.6 × Fila 2):
```
[0, 1, 0.6, -0.2, 0.4, 0] - 0.6×[0, 0, 1, 3, -1, -5]
= [0, 1, 0, -2, 1, 3]
```

**Resultado final [I | A⁻¹]**:
```
┌                          ┐
│ 1  0  0 |  0   0   1     │
│ 0  1  0 | -2   1   3     │
│ 0  0  1 |  3  -1  -5     │
└                          ┘
```

---

### **Extracción de la matriz inversa**

**Matriz A⁻¹**:
```
┌            ┐
│  0   0   1 │
│ -2   1   3 │
│  3  -1  -5 │
└            ┘
```

---

### Verificación: A × A⁻¹ = I
```
┌         ┐   ┌            ┐   ┌         ┐
│ 2  1  1 │   │  0   0   1 │   │ 1  0  0 │
│ 1  3  2 │ × │ -2   1   3 │ = │ 0  1  0 │
│ 1  0  0 │   │  3  -1  -5 │   │ 0  0  1 │
└         ┘   └            ┘   └         ┘
```

Calculando elemento (0,0):
```
(2×0) + (1×-2) + (1×3) = 0 - 2 + 3 = 1 ✓
```

---

### Resultado Final:

**Archivo de salida**: `resultado.txt`
```
Matriz original:
2.0 1.0 1.0 
1.0 3.0 2.0 
1.0 0.0 0.0 

Matriz inversa:
0.0 0.0 1.0 
-2.0 1.0 3.0 
3.0 -1.0 -5.0
```

---

## Visualización del Proceso
```
Estado inicial:    [A | I]
                   
Fila 0 procesada:  [I₁ | *]
                   [0  | *]
                   [0  | *]

Fila 1 procesada:  [I₂ | *]
                   [I₂ | *]
                   [0  | *]

Fila 2 procesada:  [I  | A⁻¹]
(IDENTIDAD)        [I  | A⁻¹]
                   [I  | A⁻¹]
```