require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += "libstdc++ mtd-utils" 
IMAGE_INSTALL += "openssh openssl openssh-sftp-server"

DISTRO_FEATURES_append = " virtualization" 
ENABLE_UART = "1"

IMAGE_INSTALL_append = " docker-ce hello-world"