from distutils.core import setup, Extension

modulopoissonmodule = Extension('modulopoisson', sources=['modulopoissonmodule.c'])
setup(name='ModulopoissonModule', version='0.1', description='Resolucion de la ecuacion de Poisson', ext_modules=[modulopoissonmodule])
