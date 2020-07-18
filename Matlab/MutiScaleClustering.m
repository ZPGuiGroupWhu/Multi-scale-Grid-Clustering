%% Read the raw data
[a1,a2,a3,a4]=textread('DS7.txt','%f%f%d%d');
[n,m] = size(a1);
X=[a1,a2];

%% Set the number of grid cells
grid_num = 32;

%% Generate the grid using the minimum and maximum of coordinates
x_gap = (max(a1)-min(a1))/grid_num;
y_gap = (max(a2)-min(a2))/grid_num;

%% Initialize the grid
result = zeros(n,1);
grid_density = zeros(grid_num,grid_num);
grid_mark = zeros(grid_num,grid_num);

%% Count the number of points of each cell
for i=1:n
    x_index = ceil((a1(i)-min(a1))/x_gap);
    y_index = ceil((a2(i)-min(a2))/y_gap);  
    if(x_index==0)
        x_index=1;
    end
    if(y_index==0)
        y_index=1;
    end
    if(x_index==grid_num+1)
        x_index=grid_num;
    end
    if(y_index==grid_num+1)
        y_index=grid_num;
    end
    grid_density(grid_num-y_index+1,x_index) = grid_density(grid_num-y_index+1,x_index)+1;
end

%% Generate the noise curve by counting the number of noise points with each threshold
test_num = 10;
noise_num = zeros(test_num,1);
for k=1:test_num
    for i=2:grid_num-1
        if(grid_density(i,1)>0&&grid_density(i,1)<=k&&grid_density(i+1,1)<=k&&grid_density(i-1,1)<=k&&grid_density(i,2)<=k&&grid_density(i+1,2)<=k&&grid_density(i-1,2)<=k)
            noise_num(k) = noise_num(k) + grid_density(i,1);
        end
        if(grid_density(i,grid_num)>0&&grid_density(i,grid_num)<=k&&grid_density(i+1,grid_num)<=k&&grid_density(i-1,grid_num)<=k&&grid_density(i,grid_num-1)<=k&&grid_density(i-1,grid_num-1)<=k&&grid_density(i+1,grid_num-1)<=k)
            noise_num(k) = noise_num(k) + grid_density(i,grid_num);
        end
        if(grid_density(1,i)>0&&grid_density(1,i)<=k&&grid_density(1,i+1)<=k&&grid_density(1,i-1)<=k&&grid_density(2,i)<=k&&grid_density(2,i-1)<=k&&grid_density(2,i+1)<=k)
            noise_num(k) = noise_num(k) + grid_density(1,i);
        end
        if(grid_density(grid_num,i)>0&&grid_density(grid_num,i)<=k&&grid_density(grid_num,i+1)<=k&&grid_density(grid_num,i-1)<=k&&grid_density(grid_num-1,i)<=k&&grid_density(grid_num-1,i-1)<=k&&grid_density(grid_num-1,i+1)<=k)
            noise_num(k) = noise_num(k) + grid_density(grid_num,i);
        end
    end
    for i=2:grid_num-1
        for j=2:grid_num-1
            if(grid_density(i,j)>0&&grid_density(i,j)<=k&&grid_density(i-1,j)<=k&&grid_density(i+1,j)<=k&&grid_density(i,j-1)<=k&&grid_density(i,j+1)<=k&&grid_density(i-1,j-1)<=k&&grid_density(i+1,j+1)<=k&&grid_density(i+1,j-1)<=k&&grid_density(i-1,j+1)<=k)
                noise_num(k) = noise_num(k) + grid_density(i,j);
            end
        end
    end
end

%% Extract the noise threshold by calculating the maximum convexity index of the curve
dif = zeros(test_num-2,1);
for i=2:test_num-1
    dif(i-1) =  abs(noise_num(i+1)+noise_num(i-1)-2*noise_num(i))/noise_num(i);
end
dif0 = zeros(test_num-1,1);
for i=2:test_num
    dif0(i-1) = noise_num(i)-noise_num(i-1);
end
noise_threshold = find(dif==max(dif))+1;

%% Set the densities of noisy cells to zero
if(grid_density(1,1)<noise_threshold&&grid_density(1,2)<noise_threshold&&grid_density(2,1)<noise_threshold&&grid_density(2,2)<noise_threshold)
    grid_density(1,1)=0;
end
if(grid_density(1,grid_num)<noise_threshold&&grid_density(1,grid_num-1)<noise_threshold&&grid_density(2,grid_num)<noise_threshold&&grid_density(2,grid_num-1)<noise_threshold)
    grid_density(1,grid_num)=0;
end
if(grid_density(grid_num,1)<noise_threshold&&grid_density(grid_num,2)<noise_threshold&&grid_density(grid_num-1,1)<noise_threshold&&grid_density(grid_num-1,2)<noise_threshold)
    grid_density(grid_num,1)=0;
end
if(grid_density(grid_num,grid_num)<noise_threshold&&grid_density(grid_num,grid_num-1)<noise_threshold&&grid_density(grid_num-1,grid_num)<noise_threshold&&grid_density(grid_num-1,grid_num-1)<noise_threshold)
    grid_density(grid_num,grid_num)=0;
end
for i=2:grid_num-1
    if(grid_density(1,i)<noise_threshold&&grid_density(1,i+1)<noise_threshold&&grid_density(1,i-1)<noise_threshold&&grid_density(2,i)<noise_threshold&&grid_density(2,i-1)<noise_threshold&&grid_density(2,i+1)<noise_threshold)
        grid_density(1,i)=0;
    end
    if(grid_density(grid_num,i)<noise_threshold&&grid_density(grid_num,i+1)<noise_threshold&&grid_density(grid_num,i-1)<noise_threshold&&grid_density(grid_num-1,i)<noise_threshold&&grid_density(grid_num-1,i-1)<noise_threshold&&grid_density(grid_num-1,i+1)<noise_threshold)
        grid_density(grid_num,i)=0;
    end
    if(grid_density(i,1)<noise_threshold&&grid_density(i+1,1)<noise_threshold&&grid_density(i-1,1)<noise_threshold&&grid_density(i,2)<noise_threshold&&grid_density(i-1,2)<noise_threshold&&grid_density(i+1,2)<noise_threshold)
        grid_density(i,1)=0;
    end
    if(grid_density(i,grid_num)<noise_threshold&&grid_density(i+1,grid_num)<noise_threshold&&grid_density(i-1,grid_num)<noise_threshold&&grid_density(i,grid_num-1)<noise_threshold&&grid_density(i-1,grid_num-1)<noise_threshold&&grid_density(i+1,grid_num-1)<noise_threshold)
        grid_density(i,grid_num)=0;
    end
end
for i=2:grid_num-1
    for j=2:grid_num-1
        if(grid_density(i,j)<noise_threshold&&grid_density(i+1,j)<noise_threshold&&grid_density(i-1,j)<noise_threshold&&grid_density(i,j-1)<noise_threshold&&grid_density(i,j+1)<noise_threshold&&grid_density(i+1,j-1)<noise_threshold&&grid_density(i+1,j+1)<noise_threshold&&grid_density(i-1,j+1)<noise_threshold&&grid_density(i-1,j-1)<noise_threshold)
            grid_density(i,j) = 0;
        end
    end
end

% copy_density = grid_density;
% for i=3:grid_num-2
%     for j=3:grid_num-2 
%         grid_density(i,j) = 1/24*(copy_density(i-2,j-2)+copy_density(i-2,j-1)+copy_density(i-2,j)+copy_density(i-2,j+1)+copy_density(i-2,j+2)+copy_density(i-1,j-2)+copy_density(i-1,j-1)+copy_density(i-1,j)+copy_density(i-1,j+1)+copy_density(i-1,j+2)+copy_density(i,j-2)+copy_density(i,j-1)+copy_density(i,j)+copy_density(i,j+1)+copy_density(i,j+2)+copy_density(i+1,j-2)+copy_density(i+1,j-1)+copy_density(i+1,j)+copy_density(i+1,j+1)+copy_density(i+1,j+2)+copy_density(i+2,j-2)+copy_density(i+2,j-1)+copy_density(i+2,j)+copy_density(i+2,j+1)+copy_density(i+2,j+2)+copy_density(i,j-2)+copy_density(i,j-1)+copy_density(i,j+1)+copy_density(i,j+2));
%     end
% end

%% Search the connected regions with 8-adjacent connection approach and assign the cluster id to cells
count = 1;
for i=1:grid_num
    for j=1:grid_num
        if (grid_density(i,j)>0)
            if(i==1)
                if(j==1)
                    grid_mark(i,j)=count;
                    count=count+1;
                else
                    if(grid_density(i,j-1)>0)
                        grid_mark(i,j)=grid_mark(i,j-1);
                    else
                        grid_mark(i,j)=count;
                        count=count+1;
                    end
                end
            else
                if(j==1)
                    if(grid_density(i-1,j)>0) 
                        grid_mark(i,j)=grid_mark(i-1,j);
                    elseif(grid_density(i-1,j)==0&&grid_density(i-1,j+1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j+1);                      %网格第一列
                    else
                        grid_mark(i,j)=count;
                        count=count+1;
                    end
                elseif(j==grid_num)
                    if(grid_density(i-1,j-1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j-1);
                    elseif(grid_density(i-1,j-1)==0&&grid_density(i,j-1)==0&&grid_density(i-1,j)>0)
                        grid_mark(i,j)=grid_mark(i-1,j);
                    elseif(grid_density(i-1,j-1)==0&&grid_density(i-1,j)==0&&grid_density(i,j-1)>0)
                        grid_mark(i,j)=grid_mark(i,j-1);
                    elseif(grid_density(i-1,j-1)==0&&grid_density(i-1,j)>0&&grid_density(i,j-1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j);
                        if(grid_mark(i-1,j)~=grid_mark(i,j-1))
                            temp = grid_mark(i,j-1);
                            for k=1:i
                                for r=1:grid_num
                                    if(grid_mark(k,r)==temp)
                                        grid_mark(k,r)=grid_mark(i,j);
                                    end		
                                end
                            end
                        end
                    else
                        grid_mark(i,j)=count;
                        count=count+1;
                    end                                                      %网格最右边一列
                else
                    if(grid_density(i-1,j)==0&&grid_density(i,j-1)==0&&grid_density(i-1,j-1)==0&&grid_density(i-1,j+1)==0)
                        grid_mark(i,j)=count;
                        count=count+1;
                    elseif(grid_density(i-1,j)>0)
                        grid_mark(i,j)=grid_mark(i-1,j);
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)==0&&grid_density(i-1,j-1)==0&&grid_density(i-1,j+1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j+1);
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)==0&&grid_density(i-1,j-1)>0&&grid_density(i-1,j+1)==0)
                        grid_mark(i,j)=grid_mark(i-1,j-1);
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)>0&&grid_density(i-1,j-1)==0&&grid_density(i-1,j+1)==0)
                        grid_mark(i,j)=grid_mark(i,j-1);  
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)>0&&grid_density(i-1,j-1)>0&&grid_density(i-1,j+1)==0)
                        grid_mark(i,j)=grid_mark(i-1,j-1);
                        if(grid_mark(i,j-1)~=grid_mark(i-1,j-1))
                            temp = grid_mark(i,j-1);
                            for k=1:i
                                for r=1:grid_num
                                    if(grid_mark(k,r)==temp)
                                        grid_mark(k,r)=grid_mark(i,j);
                                    end		
                                end
                            end
                        end
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)>0&&grid_density(i-1,j-1)==0&&grid_density(i-1,j+1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j+1);
                        if(grid_mark(i,j-1)~=grid_mark(i-1,j+1))
                            temp = grid_mark(i,j-1);
                            for k=1:i
                                for r=1:grid_num
                                    if(grid_mark(k,r)==temp)
                                        grid_mark(k,r)=grid_mark(i,j);
                                    end		
                                end
                            end
                        end
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)==0&&grid_density(i-1,j-1)>0&&grid_density(i-1,j+1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j-1);
                        if(grid_mark(i,j-1)~=grid_mark(i-1,j+1))
                            temp = grid_mark(i-1,j+1);
                            for k=1:i
                                for r=1:grid_num
                                    if(grid_mark(k,r)==temp)
                                        grid_mark(k,r)=grid_mark(i,j);
                                    end		
                                end
                            end
                        end
                    elseif(grid_density(i-1,j)==0&&grid_density(i,j-1)>0&&grid_density(i-1,j-1)>0&&grid_density(i-1,j+1)>0)
                        grid_mark(i,j)=grid_mark(i-1,j-1);
                        if(grid_mark(i-1,j-1)~=grid_mark(i-1,j+1))
                            temp = grid_mark(i-1,j+1);
                            for k=1:i
                                for r=1:grid_num
                                    if(grid_mark(k,r)==temp)
                                        grid_mark(k,r)=grid_mark(i,j);
                                    end		
                                end
                            end
                        end                   
                    end
                end            
            end
        end
    end
end

%% Assign the cluster id to points
for i=1:n
    x_index = ceil((a1(i)-min(a1))/x_gap);
    y_index = ceil((a2(i)-min(a2))/y_gap);
    if(x_index==0)
        x_index=1;
    end
    if(y_index==0)
        y_index=1;
    end
    if(x_index==grid_num+1)
        x_index=grid_num;
    end
    if(y_index==grid_num+1)
        y_index=grid_num;
    end
    result(i) = grid_mark(grid_num-y_index+1,x_index);
end

%% Adjust the cluster id to continuous positive integers from 1 to n
mark_temp = 1;
storage = zeros(n,1);
for i=1:n
    storage(i) = -1;
end
for i=1:n
    if(result(i)>0)
        if (ismember(result(i),storage)==0)
            storage(i) = result(i);
            result(i) = mark_temp;
            mark_temp = mark_temp+1;
        else
            index = find(storage==result(i));
            result(i) = result(index);
        end
    end
end

%% Visualize the clustering results
plotcluster(n,X,result);