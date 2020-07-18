function [ ] = plotcluster( n,X,a3 )  
% MarkerFaceColors = Lines(clusnum);
% MarkerEdgeColors = Lines(clusnum);
for i=1:n
    if(a3(i)==0||a3(i)==-1)
        plot(X(i,1),X(i,2),'k*','markersize',5);
        hold on;
    elseif(a3(i)==2)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.9,0.1,0.3],'markeredgecolor',[0.9,0.1,0.3],'markersize',5);
        hold on;
    elseif(a3(i)==3)
%         plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.4,0],'markeredgecolor',[1,0.4,0],'markersize',5);
%         hold on;
          plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.7,0.7],'markeredgecolor',[0,0.7,0.7],'markersize',5);
          hold on;
    elseif(a3(i)==4)
%         plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.27,0],'markeredgecolor',[1,0.27,0],'markersize',5);
%         hold on;
          plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.3,0],'markeredgecolor',[1,0.3,0],'markersize',5);
          hold on;
    elseif(a3(i)==10)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.3,0.3,1],'markeredgecolor',[0.3,0.3,1],'markersize',5);
        hold on;
    elseif(a3(i)==5)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.6,0,0.6],'markeredgecolor',[0.6,0,0.6],'markersize',5);
        hold on;
    elseif(a3(i)==6)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.6,0],'markeredgecolor',[0,0.6,0],'markersize',5);
        hold on;
    elseif(a3(i)==7)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.1,1],'markeredgecolor',[0,0.1,1],'markersize',5);
        hold on;
    elseif(a3(i)==8)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.8,0.1],'markeredgecolor',[1,0.8,0.1],'markersize',5);
        hold on;
    elseif(a3(i)==1)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.4,0.6],'markeredgecolor',[1,0.4,0.6],'markersize',5);
        hold on;
     elseif(a3(i)==9)
         plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.2,0.8],'markeredgecolor',[0,0.2,0.8],'markersize',5);
         hold on;
    elseif(a3(i)==11)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.6,0,0.6],'markeredgecolor',[0.6,0,0.6],'markersize',5);
        hold on;
    elseif(a3(i)==12)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.65,0],'markeredgecolor',[0,0.65,0],'markersize',5);
        hold on;  
     elseif(a3(i)==13)
         plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.7,0],'markeredgecolor',[0,0.7,0],'markersize',5);
         hold on; 
    elseif(a3(i)==14)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.75,1],'markeredgecolor',[0,0.75,1],'markersize',5);
        hold on;
    elseif(a3(i)==15)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.6,0.6,0],'markeredgecolor',[0.6,0.6,0],'markersize',5);
        hold on;
    elseif(a3(i)==16)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.1,0.1,0.9],'markeredgecolor',[0.1,0.1,0.9],'markersize',5);
        hold on;  
    elseif(a3(i)==18)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.1,0.4,0.7],'markeredgecolor',[0.1,0.4,0.7],'markersize',5);
        hold on; 
    elseif(a3(i)==17)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.4,0],'markeredgecolor',[1,0.4,0],'markersize',5);
        hold on;
    elseif(a3(i)==19)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0,0.98,0.6],'markeredgecolor',[0,0.98,0.6],'markersize',5);
        hold on;  
    elseif(a3(i)==20)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.78,0.1,0.52],'markeredgecolor',[0.78,0.1,0.52],'markersize',5);
        hold on; 
    elseif(a3(i)==21)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.6,0.2,0.8],'markeredgecolor',[0.6,0.2,0.8],'markersize',5);
        hold on;  
    elseif(a3(i)==22)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.1,0.18],'markeredgecolor',[1,0.1,0.18],'markersize',5);
        hold on; 
    elseif(a3(i)==23)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.1,0.18],'markeredgecolor',[1,0.1,0.18],'markersize',5);
        hold on;
    elseif(a3(i)==24)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[0.2,0.2,0.8],'markeredgecolor',[0.2,0.2,0.8],'markersize',5);
        hold on; 
    elseif(a3(i)==25)
        plot(X(i,1),X(i,2),'o','markerfacecolor',[1,0.4,0],'markeredgecolor',[1,0.4,0],'markersize',5);
        hold on;  
    elseif(a3(i)==26)
        plot(X(i,1),X(i,2),'o','markerfacecolor','r','markeredgecolor','r','markersize',5);
        hold on; 
    elseif(a3(i)==27)
        plot(X(i,1),X(i,2),'o','markerfacecolor','b','markeredgecolor','b','markersize',5);
        hold on; 
    elseif(a3(i)==28)
        plot(X(i,1),X(i,2),'o','markerfacecolor','g','markeredgecolor','g','markersize',5);
        hold on; 
    elseif(a3(i)==29)
        plot(X(i,1),X(i,2),'o','markerfacecolor','y','markeredgecolor','y','markersize',5);
        hold on; 
    elseif(a3(i)==30)
        plot(X(i,1),X(i,2),'o','markerfacecolor','m','markeredgecolor','m','markersize',5);
        hold on; 
    elseif(a3(i)==31)
        plot(X(i,1),X(i,2),'o','markerfacecolor','c','markeredgecolor','c','markersize',5);
        hold on; 
    elseif(a3(i)==32)
        plot(X(i,1),X(i,2),'o','markerfacecolor','k','markeredgecolor','k','markersize',5);
        hold on; 
    elseif(a3(i)>32&&mod(a3(i),6)==0)
        plot(X(i,1),X(i,2),'o','markerfacecolor','g','markeredgecolor','g','markersize',5);
        hold on;
    elseif(a3(i)>32&&mod(a3(i),6)==1)
        plot(X(i,1),X(i,2),'o','markerfacecolor','r','markeredgecolor','r','markersize',5);
        hold on;
    elseif(a3(i)>32&&mod(a3(i),6)==2)
        plot(X(i,1),X(i,2),'o','markerfacecolor','b','markeredgecolor','b','markersize',5);
        hold on;
    elseif(a3(i)>32&&mod(a3(i),6)==3)
        plot(X(i,1),X(i,2),'o','markerfacecolor','y','markeredgecolor','y','markersize',5);
        hold on;
    elseif(a3(i)>32&&mod(a3(i),6)==4)
        plot(X(i,1),X(i,2),'o','markerfacecolor','c','markeredgecolor','c','markersize',5);
        hold on;
    elseif(a3(i)>32&&mod(a3(i),6)==5)
        plot(X(i,1),X(i,2),'o','markerfacecolor','m','markeredgecolor','m','markersize',5);
        hold on;
    end
end
end

