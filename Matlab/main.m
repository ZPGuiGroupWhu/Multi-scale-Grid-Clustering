%% Read the raw data
data = textread('../cluster_data1.txt');
[n,~] = size(data);
X = data(:,1:2);
ref = data(:,3); 

%% Set the number of grid cells
grid_num = 27;

%% Conduct MSGC algorithm
result = MSGC(X,grid_num);

%% Visualize the clustering results
plotcluster(X, result);

[ACC, NMI, ARI, Fscore, JI, RI] = ClustEval(ref, result);

figure(2);
y = [ACC, NMI, ARI, Fscore, JI, RI];
bar([ACC, NMI, ARI, Fscore, JI, RI]);
set(gca,'xticklabel',{'ACC','NMI','ARI','Fscore','JI','RI'})