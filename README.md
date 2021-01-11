# Fractal Drawer

Desktop application that draws particular user-defined fractal sets using Java's Fork/Join api
which, in fact, allows different threads to take adventage of multiple cores.

For now there are only two fractals: Julia Set and Mandelbrot Set. Anyone can simply define their own
fractals via creating class derived from `app.Fractal`.

Main settings contain properties like:
    - Image scalling
    - Number of running threads
    - Fractal selection

Example below:

![img1](https://github.com/ZeroMaster28/fractal-drawer/blob/master/example.jpg)