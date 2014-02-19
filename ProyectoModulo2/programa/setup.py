# coding: utf-8
# 
# SP2118
# Confg del mod en C para la resolucion de la ecuacion de Poisson
#

from distutils.core import setup, Extension

modulopoissonmodule = Extension('modulopoisson', sources=['modulopoissonmodule.c'])

setup(name='ModulopoissonModule',
    version='0.1',
    description='Resolucion de la ecuacion de Poisson',
    ext_modules=[modulopoissonmodule]
    )
