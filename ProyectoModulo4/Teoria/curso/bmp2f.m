%  Leer un mapa de bits y guardar en un archivo
% cada uno de sus pixeles, en cada capa.
%
% CNCA, 2014.
%

function I = bmp2f(archivoEntrada, archivoSalida)
image = archivoEntrada;
I = imread(image);
X=size(I,1);
Y=size(I,2);
Z=size(I,3);

file = archivoSalida;
fid = fopen(file,"w");

fprintf(fid,"%i\n%i\n%i\n",X,Y,Z);

for i=1:X
	for j=1:Y
		for k=1:Z
			fprintf(fid,"%i\n",I(i,j,k));
		end
	end
end

fclose(fid);

endfunction
