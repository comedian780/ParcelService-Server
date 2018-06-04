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
     docker network create --driver bridge ParcelService
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

   config.vm.provision "docker" do |d|
   d.run "parcelservice-database", args: "-d -p 3306:3306 --restart always --network=ParcelService --name=db"
   d.run "parcelservice-server", args: "-d -p 8443:8443 --restart always --network=ParcelService --name=rest java -jar ParSer-Server-1.0.jar"
   d.run "parcelservice-frontend", args: "-d -p 80:80 --restart always--network=ParcelService --name=webserver"
   end
end
