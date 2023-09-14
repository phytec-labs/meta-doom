This README file contains information on the contents of the
meta-doom layer.

### Freedoom recipe originally from this project:
https://github.com/geoffrey-vl/meta-doom

### Video output learned from this project:
https://gitlab.com/bguan/meta-doom


Dependencies
============

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

  URI: git://git.yoctoproject.org/xxxx
  layers: xxxx
  branch: master


Table of Contents
=================

  I. Adding the meta-doom layer to your build
 II. Misc


## I. Adding the meta-doom layer to your build

In order to use this layer, you need to make the build system aware of
it.

Assuming the meta-doom layer exists at the top-level of your
yocto build tree, you can add it to the build system by adding the
location of the meta-doom layer to bblayers.conf, along with any
other layers needed. e.g.:

```sh
BBLAYERS ?= " \
  /path/to/yocto/meta \
  /path/to/yocto/meta-poky \
  /path/to/yocto/meta-yocto-bsp \
  /path/to/yocto/meta-doom \
  "
```

Edit youre build/local.conf and add the following:

```sh
IMAGE_INSTALL:append = " \
  freedoom \
  chocolate-doom \
  libsdl-net \
"
```

### DirectFB (framebuffer)
Append the following lines to your `local.conf`.

```sh
MACHINE ?= "phyboard-lyra-am62xx-2"

DISTRO_FEATURES:append = " directfb"
DISTRO_FEATURES:remove = "x11"

# WORKAROUND: DirectFB libraries are not installed although libsdl2 has it in its DEPENDS.
IMAGE_INSTALL:append = " directfb"
```

### X11
Append the following lines to your `local.conf`.

```sh
MACHINE ?= "phyboard-lyra-am62xx-2"

IMAGE_FEATURES += "x11-base"
```

## Build

Source build environment and build _phytec-headless-image_.

```sh
cd poky
source oe-init-build-env
bitbake phytec-headless-image
```

**Note:** Because of a so far unknown reason the DirectFB libraries are not installed even though _libsdl2_ has _directfb_ in its DEPENDS as well as in its PACKAGECONFIG. Manually adding _directfb_ installs the libraries.

## Run

### DirectFB (framebuffer)

```
chocolate-doom
```

### X11

```
DISPLAY=:0 chocolate-doom
```
