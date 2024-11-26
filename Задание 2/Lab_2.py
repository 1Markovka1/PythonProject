#Сортировка выбором

import random
import time

array_size = int(input("Введите размер массива: "))
random_array = [random.randint(1, 2) for _ in range(array_size)]

#print(f"Сгенерированный массив: {random_array}")

def selection_sort(arr):
    n = len(arr)
    for i in range(n):
        min_index = i
        for j in range(i + 1, n):
            if arr[j] < arr[min_index]:
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]

s_time = time.time()
selection_sort(random_array)
e_time = time.time()

#print(f"Отсортированный массив: {random_array}")

_time = e_time - s_time
print(f"Время выполнения сортировки: {_time:.2f} секунд")
    
