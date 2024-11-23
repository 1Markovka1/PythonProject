class geometric:
    def __init__(self, long, shirina):
        self.long=long
        self.shirina=shirina
    def ploshad(self):
        return self.long * self.shirina
dl=geometric(5,7)
print('Длина и ширина =',dl.long, dl.shirina)
print('Площадь =',dl.ploshad())
