def Numbers(b, c):
    a = int(input("Введите число: "))
    if a >= 0:
        x = max(a, b, c)
        return x
    else:
        return -1

print("Максимальное число: ", Numbers(2, 1))
