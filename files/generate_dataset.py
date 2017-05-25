#!/usr/bin/python2.7

from random import randint

file_path = "./vegetable_parameters.arff"
base_value = {'agua': 6, 'luz': 10, 'temperatura': 24, 'tierra': 3, 'nota': 1}
tierra_types = {0: 'arcilloso', 1: 'limoso', 2: 'arenoso', 3: 'margoso', 4: 'gredoso', 5: 'pantanoso'}
nota_types = {0: 'A', 1: 'B', 2: 'C', 3: 'D'}


def create_header(fil):
    fil.write("@relation vegetables_grow\n\n")
    fil.write("@attribute 'agua' numeric\n")
    fil.write("% A numeric value measuring the litres per square centimeter in the greenhouse\n\n")
    fil.write("@attribute 'luz' numeric\n")
    fil.write("% A numeric value measuring the hours of exposition to light of the greenhouse\n\n")
    fil.write("@attribute 'temperatura' numeric\n")
    fil.write("% A numeric value measuring the mean temperature of the greenhouse\n\n")
    fil.write("@attribute 'tierra' {arcilloso, limoso, arenoso, margoso, gredoso, pantanoso}\n")
    fil.write("% A nominal type that establishes the type of ground used on the greenhouse\n\n")
    fil.write("@attribute 'nota' {A, B, C, D}\n")
    fil.write("% A nominal value measuring the category achieved by the vegetables of the greenhouse,\n")
    fil.write("% being A the highest and D the lowest scores.\n\n")
    fil.write("@data\n")


def generate_noise():
    agua = base_value['agua'] + randint(-2, 2)
    luz = base_value['luz'] + randint(-3, 3)
    temperatura = base_value['temperatura'] + randint(-5, 7)
    tierra = base_value['tierra'] + randint(0, 1)
    nota = 0.0
    if agua <= base_value['agua']-1 | agua >= base_value['agua']+1:
        nota += 0.5
    if luz <= base_value['luz']-1 | luz >= base_value['luz']+1:
        nota += 0.5
    if temperatura <= base_value['temperatura']-1 | temperatura >= base_value['temperatura']+1:
        nota += 0.5
    if base_value['tierra'] != tierra:
        nota += 1
    notaza = nota_types[int(round(nota))]
    ret = ""
    ret += str(agua) + ',' \
           + str(luz) + ',' \
           + str(temperatura) \
           + ',' + str(tierra_types[tierra]) \
           + ',' + str(notaza) + "\n"
    print ret
    return ret


if __name__ == "__main__":
    dataset = open(file_path, "w")
    create_header(dataset)
    for i in range(0, 200):
        line = generate_noise()
        dataset.write(line)
    dataset.close()
