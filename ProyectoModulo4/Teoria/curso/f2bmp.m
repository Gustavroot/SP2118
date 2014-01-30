% Generar un Bitmap a partir de un archivo de bordes
%
% CNCA, 2014.

function M = f2bmp(archivoEntrada, archivoSalida)
	fid = fopen(archivoEntrada,"r");
	arch = fscanf(fid, '%i');
	iter = 1;

	X = arch(iter++);
	Y = arch(iter++);
	iter++;

	for i=1:X
		for j=1:Y	
			M(i,j) = arch(iter++);
		end
	end

	fclose(fid);
	imwrite(M,archivoSalida,'bmp');

endfunction
