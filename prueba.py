from sys import stdin

def minimize_recursive(sequence, j, m):
    #Caso base si se queda sin cambios
    if m == 0:
        return sum(sequence[:j]), sequence
    
    best_sum = sum(sequence[:j])
    best_seq = sequence[:]
    #revisar todos los casos de intercambios
    for i in range(len(sequence) - 1):
        new_seq = sequence[:]
        new_seq[i], new_seq[i + 1] = new_seq[i + 1], new_seq[i]
        #sacar el minimo de la nueva secuencia y reducir en 1 el numero de cambios
        new_sum, new_arrangement = minimize_recursive(new_seq, j, m - 1)
        #se compara el minimo de la nueva secuencia con el mejor minimo
        #en caso de ser menor se actualiza el mejor minimo y la mejor secuencia
        if new_sum < best_sum:
            best_sum = new_sum
            best_seq = new_arrangement
    
    return best_sum, best_seq

def main():
    linea = stdin.readline().strip().split()
    casos = int(linea[0])
    resultados = []
    for _ in range(casos):
        linea = stdin.readline().strip().split()
        n = int(linea[0])  # número de jugadores
        j = int(linea[1])  # combinación de
        m = int(linea[2])  # cambios admitidos
        pesos = []
        for i in range(3, len(linea)):
            pesos.append(int(linea[i]))
        min_sum = minimize_recursive(pesos, j, m)
        resultados.append(min_sum)
    for res in resultados:
        print(res)

main()