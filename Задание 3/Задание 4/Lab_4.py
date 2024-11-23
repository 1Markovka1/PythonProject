mass = [18, 4, 12, 7, 16, 10, 5, 19, 2]
mass_print=[]
numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print('Введите название действия!')
print('Получить элемент массива по индексу n')
print('Получить элементы с n по b с шагом v')
print('Получить n-ый элемент с конца массива')
#Ввести: "Получить элементы с 5 по 7 с шагом 1"
a=input()
n=b=v=0
def f(mass):
    for i in range(len(numbers)):
        if str(i) in a:
            if 'Получить элемент массива по индексу' in a:
                print('Элемент массива по индексу =',mass[i])
                break
            elif 'Получить элементы' in a:
                n=a.replace('Получить элементы с','')
                n=n.replace('по','')
                n=n.replace('с шагом','')
                n,b,v=n.split()
                n=int(n)
                b=int(b)
                v=int(v)
                if n < b and b!=0 and v!=0 and n>=0:
                    for l in range(n,b,v):
                        mass_print.append(mass[l])
                        l+=1
                    print(mass_print)
                    break
            elif 'с конца массива' in a:
                print('Элемент с конца массива =', mass[-i])
                break
                
    else:
        print('Введено не правильное значение или команда')

f(mass)
