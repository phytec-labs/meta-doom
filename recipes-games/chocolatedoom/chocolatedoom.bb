DESCRIPTION = "Chocolate Doom is a Doom source port that accurately reproduces the original Doom game as it was played in the 1990s."
HOMEPAGE = "https://www.chocolate-doom.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=60d644347832d2dd9534761f6919e2a6"
SECTION = "games"

SRC_URI = "git://github.com/chocolate-doom/chocolate-doom.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

DEPENDS = "virtual/libsdl2 libsdl2-mixer libsdl2-net pkgconfig pkgconfig-native automake-native autoconf-native autoconf-archive-native libtool-native"

S = "${WORKDIR}/git"

inherit autotools

do_configure() {
    cd ${S}
    ./autogen.sh --host=aarch64-phytec-linux
    oe_runconf --host=aarch64-phytec-linux
}

do_compile () {
    cd ${S}
    make
}

do_install () {
    cd ${S}
    make DESTDIR=${D} install
}

FILES:${PN} += "${bindir}/chocolate-doom \
                ${datadir}/icons/hicolor/128x128/apps/*.png \
                ${datadir}/metainfo/*.xml \
                ${datadir}/bash-completion/completions/*"

