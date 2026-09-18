import math


# EJERCICIO N°12

class LiquidacionEmpleado:
    def __init__(self, horas_trabajadas, tarifa_hora, porcentaje_retencion):
        self.horas_trabajadas = horas_trabajadas
        self.tarifa_hora = tarifa_hora
        self.porcentaje_retencion = porcentaje_retencion

    def obtener_salario_bruto(self):
        return self.horas_trabajadas * self.tarifa_hora

    def obtener_retencion(self):
        return self.obtener_salario_bruto() * (self.porcentaje_retencion / 100)

    def obtener_salario_neto(self):
        return self.obtener_salario_bruto() - self.obtener_retencion()

    def mostrar_informe(self):
        print(f"Salario Bruto: ${self.obtener_salario_bruto():.2f}")
        print(f"Retención en la fuente: ${self.obtener_retencion():.2f}")
        print(f"Salario Neto: ${self.obtener_salario_neto():.2f}")

# Ejecución
empleado = LiquidacionEmpleado(48, 5000, 12.5)
empleado.mostrar_informe()


# EJERCICIO N°14

class CalculadoraPotencias:
    def __init__(self, numero):
        self.numero = numero

    def cuadrado(self):
        return self.numero ** 2

    def cubo(self):
        return self.numero ** 3

    def mostrar_potencias(self):
        print(f"Número base: {self.numero}")
        print(f"Cuadrado: {self.cuadrado()}")
        print(f"Cubo: {self.cubo()}")

# Ejecución
potencias = CalculadoraPotencias(7) # Ejemplo con el número 7
potencias.mostrar_potencias()


#EJERCICIO N°17


class Circulo:
    def __init__(self, radio):
        self.radio = radio

    def calcular_area(self):
        return math.pi * (self.radio ** 2)

    def calcular_perimetro(self):
        return 2 * math.pi * self.radio

    def mostrar_metricas(self):
        print(f"Radio del círculo: {self.radio}")
        print(f"Área: {self.calcular_area():.4f}")
        print(f"Longitud de circunferencia: {self.calcular_perimetro():.4f}")

# Ejecución
mi_circulo = Circulo(15) # Ejemplo con un radio de 15
mi_circulo.mostrar_metricas()