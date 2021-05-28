# Hello World avec Yocto
Il s'agit de construire une image en utilisant Yocto, de l'upload sur RPi3 et d'y ajouter un programme simple Hello World.

![image](https://user-images.githubusercontent.com/72506988/117007193-a45f5280-ace9-11eb-8cdb-7e39112cf5e6.png)

---

## Récupération
Récupérer le dossier **meta-ynov-rpi4** ainsi que le fichier **local.conf** présents dans ce repo.

## Installation des paquets nécessaires pour Yocto
```
sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint3 xterm python3-subunit mesa-common-dev  
```
```
sudo apt install python3-pip
```

## Installation Yocto
> mkdir ~/yocto  
> cd yocto  
> git clone -b gatesgarth git://git.yoctoproject.org/poky.git  
> git clone -b gatesgarth git://git.openembedded.org/meta-openembedded  

## Installation meta-layer pour RPi
> git clone -b gatesgarth git://git.yoctoproject.org/meta-raspberrypi

## Environnement Yocto 
> source ~/yocto/poky/oe-init-build-env ~/build-ynov-rpi  

### Ajouts des layers
> cd ~/build-ynov-rpi  
> bitbake-layers add-layer ~/yocto/raspberrypi  
> bitbake-layers add-layer ~/yocto/meta-openembedded/meta-oe   
> bitbake-layers add-layer ~/yocto/meta-openembedded/meta-networking   
> bitbake-layers add-layer ~/yocto/meta-openembedded/meta-python  
> bitbake-layers add-layer ~/yocto/meta-openembedded/meta-filesystems   
> bitbake-layers add-layer ~/yocto/meta-openembedded/meta-multimedia   
> bitbake-layers add-layer ~/meta-ynov-rpi4  

Pour vérifier le bon ajout des layers :
> ~/build-ynov-rpi/conf/bblayers.conf

Ce fichier devrait ressembler à ceci :
```
# POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
# changes incompatibly
POKY_BBLAYERS_CONF_VERSION = "2"

BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  /home/moise/yocto/poky/meta \
  /home/moise/yocto/poky/meta-poky \
  /home/moise/yocto/poky/meta-yocto-bsp \
  /home/moise/yocto/meta-raspberrypi \
  /home/moise/yocto/meta-openembedded/meta-oe \
  /home/moise/yocto/meta-openembedded/meta-networking \
  /home/moise/yocto/meta-openembedded/meta-python \
  /home/moise/yocto/meta-openembedded/meta-filesystems \
  /home/moise/yocto/meta-openembedded/meta-multimedia \
  /home/moise/meta-ynov-rpi4 \
```
*/home/moise/* peut varier.

### Ajout layer pour utiliser Docker

> git clone -b gatesgarth git://git.yoctoproject.org/meta-virtualization ~/yocto/meta-virtualization

> cd ~/build-ynov-rpi  
> bitbake-layers add-layer ~/yocto/meta-virtualization


## local.conf
Dans **~/build-ynov-rpi/conf**, remplacer le fichier `local.conf` par celui présent dans ce repo, dans le dossier **configs**.


## Build
> cd ~/build-ynov-rpi   
> bitbake ynov-rpi4-image


## Upload dans carte microSD 
Connecter la carte microSD au PC puis repérer le nom de la carte microSD (en sdX) :
> df -h   

Dans **~/build-ynov-rpi/tmp/deploy/images/raspberrypi3**, repérer le fichier avec la terminologie .rootfs.rpi-sdimg, puis : 
> dd if=MonImage.rootfs.rpi-sdimg of=/dev/sdX bs=4M   

En remplaçant :
- **MonImage** par le nom de l'image du fichier repéré plus tôt dans **~/build-ynov-rpi/tmp/deploy/images/raspberrypi3**  
- **sdX** par le nom repéré de la carte microSD avec la commande `df -h`


## Essai de l'image   
- Placer la carte microSD dans la RPi   
- Brancher un écran et un clavier à la RPi   
- Alimenter la RPi   
- Patienter pendant le démarrage jusqu'à la demande de login et entrer : root   
- Tester le programme Hello World en tapant : hello   

---

# Source
https://github.com/yassine-elmernissi/bachelor-embedded-linux/tree/main/labs/lab2
