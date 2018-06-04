Vagrant.configure(2) do |config|
  config.vm.box = "ubuntu/trusty64"
  config.vm.network "forwarded_port", guest: 80, host: 8085
  config.vm.network "private_network", ip: "192.168.56.101",:name => 'vboxnet0',:adapter => 2
  #config.vm.synced_folder "vagrant/www/", "/var/www"

  config.vm.provider "virtualbox" do |vb|
  vb.gui = false
  vb.name="testemonial-server"
  vb.memory = "4096"
  end

  config.vm.provision "shell", run: "always", inline: <<-SHELL
    sudo apt-get update
    sudo apt-get upgrade -y
    SHELL

  config.vm.provision "docker" do |d|
  end

  config.vm.provision "shell", inline: <<-SHELL
     sudo usermod -aG docker $USER
     sudo docker network create ParcelService
   SHELL

   config.vm.provision "shell", run: "always", inline: <<-SHELL
   wget 192.168.56.100/images/frontend.tar
   wget 192.168.56.100/images/server.tar
   wget 192.168.56.100/images/database.tar
   sudo docker load < frontend.tar
   sudo docker load < server.tar
   sudo docker load < database.tar
   rm frontend.tar
   rm server.tar
   rm database.tar
   SHELL

   config.vm.provision "shell", run: "always", inline: <<-SHELL
   docker network create --driver bridge ParcelService
   docker run parcelservice-frontend -d -p 80:80 --network=ParcelService --name=webserver
   docker run parcelservice-server -d -p 8443:8443 --network=ParcelService --name=rest java -jar ParSer-Server-1.0.jar
   docker run parcelservice-database -d -p 3306:3306 --network=ParcelService --name=db
   SHELL
end
