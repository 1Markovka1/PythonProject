class geometric:
    def __init__(self, long, shirina, visota, radius):
        self.long = long
        self.shirina = shirina
        self.visota = visota
        self.radius = radius
        
class ploshad_pryamo(geometric):
    def ploshad_pryamo(self):
        return self.long * self.shirina

class ploshad_pryamo(geometric):
    def ploshad_pryamo(self):
        return self.long * self.shirina

class ploshad_crug(geometric):
    def ploshad_crug(self):
        return (self.radius)**2 * 3.14
    
class ploshad_romb(geometric):
    def ploshad_romb(self):
        return self.long * self.visota

dl = ploshad_pryamo(5,7,0,0)
print('Стороны прямоугольника =',dl.long,dl.shirina)
print('Площадь прямоугольника =',dl.ploshad_pryamo(),'\n')

cicrle = ploshad_crug(0,0,0,5)
print('Радиус круга =',dl.long,dl.shirina)
print('Площадь круга =',cicrle.ploshad_crug(),'\n')

romb = ploshad_romb(5,0,5,0)
print('Сторона и высота =',dl.long,dl.visota)
print('Площадь ромба =',romb.ploshad_romb(),'\n')
