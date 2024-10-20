# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-bennowotny.git;protocol=ssh;branch=main \
           file://0001-only-build-misc-modules-and-scull.patch \
           file://0001-use-updated-KERNEL_SRC-variables-for-kirkstone.patch \
           file://ldd-scull \
           "
# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "d980877eb69c1ad6d90ce187f17916ef3585ede1"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-scull"
EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "ldd-scull"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

FILES:${PN} += "${sysconfdir}/init.d/ldd-scull"

do_install:append(){
  install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ldd-scull ${D}${sysconfdir}/init.d
}
