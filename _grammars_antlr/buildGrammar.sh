#!/bin/bash	

	source ~/BashUtils.sh
	

	declare -a _args=("$@")

	BashUtils_decodeDashDashArguments _args
	BashUtils_echoBashSignature "###" $0 _args


	antlr_uPFam="${_args[0]}"
	antlr_uP="${_args[1]}"
	antlr_uPVer="${_args[2]}"
	antlr_asmbVer="${_args[3]}"
	antlr_dirsVer="${_args[4]}"
	antlr_exprVer="${_args[5]}"
	antlr_cmd="${_args[6]}"


	ProjectBase_PSpec="${_cwgBase__Home}"
	ANTLRTool_FSpec="${_cwgBase}/Eclipse/antlr-4.13.2-complete.jar"

    GrammerSource_DSpec="${ProjectBase_PSpec}/Computer-Architecture/Retro-Computing/XA/grammars"
    GrammerCompileDSpec="${ProjectBase_PSpec}/Computer-Architecture/Retro-Computing/XA/__eclipse/XA/target/generated-sources/antlr4"
	GrammerWorkspaceDSpec="${ProjectBase_PSpec}/Computer-Architecture/Retro-Computing/XA/grammars/__workspace"
	
    	
    bashParmRead=false

	if [[ -z "${antlr_uPFam}" ]] ;  then
		
		# Build list of microprocessor familiess ...
		declare -A uPFams
		for dir in ${GrammerSource_DSpec}/processors/*/ ;  do
			uPFam=$( sed -e "s|${GrammerSource_DSpec}/processors/_||" -e "s|/||" <<< ${dir} )
			uPFams[${uPFam}]=${uPFam}
		done

		# Display microprocessor families ...
	    echo ""
		n=1
		for uPFam in $( printf "%s\n" ${!uPFams[@]}  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${uPFam}
			((n++))
		done

	    read  -p "uP Family []? " antlr_uPFam_
		bashParmRead=true
	
		# Decode microprocessor family selected ...
    	n=1
		for uPFam in $( printf "%s\n" ${!uPFams[@]}  |  sort ) ;  do
		    if [[ ${antlr_uPFam_} -eq ${n} ]] ;  then antlr_uPFam=${uPFam} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_uP}" ]] ;  then

		# Build list of microprocessors ...
		declare -A uPs
		for file in ${GrammerSource_DSpec}/processors/_${antlr_uPFam}/* ;  do
			uP=$( sed -e "s|${GrammerSource_DSpec}/processors/_${antlr_uPFam}/||" -e "s|_v*.*||" <<< ${file} )
			uPs[${uP}]=${uP}
		done

		# Display microprocessors ...
	    echo ""
		n=1
		for uP in $( printf "%s\n" ${!uPs[@]}  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${uP}
			((n++))
		done

		read -p "Microprocessor []: " antlr_uP_
		bashParmRead=true
	
		# Decode microprocessor selected ...
    	n=1
		for uP in $( printf "%s\n" ${!uPs[@]}  |  sort ) ;  do
		    if [[ ${antlr_uP_} -eq ${n} ]] ;  then antlr_uP=${uP} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_uPVer}" ]] ;  then

		# Build list of microprocessors grammer versions...
		declare -A uPVers
		for file in ${GrammerSource_DSpec}/processors/_${antlr_uPFam}/${antlr_uP}_* ;  do
			uPVer=$( sed -e "s|${GrammerSource_DSpec}/processors/_${antlr_uPFam}/${antlr_uP}_||" -e "s|\.[^.]*$||" <<< ${file} )
			uPVers[${uPVer}]=${uPVer}
		done

		# Display microprocessors grammer versions ...
	    echo ""
		n=1
		for uPVer in $( printf "%s\n" ${!uPVers[@]}  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${uPVer}
			((n++))
		done

		read -p "${antlr_uP} Version [$((n -1))]: " antlr_uPVer_
		bashParmRead=true
	
		# Decode microprocessors grammer version selected ...
 		if [[ -z "${antlr_uPVer_}" ]] ;  then antlr_uPVer_=$((n -1)) ;  fi

   		n=1
		for uPVer in $( printf "%s\n" ${!uPVers[@]}  |  sort ) ;  do
		    if [[ ${antlr_uPVer_} -eq ${n} ]] ;  then antlr_uPVer=${uPVer} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_asmbVer}" ]] ;  then

		# Build list of assembly grammer versions...
		declare -A asmbVers
		for file in ${GrammerSource_DSpec}/_assemblies/* ;  do
			asmbVer=$( sed -e "s|${GrammerSource_DSpec}/_assemblies/Assembly_||" -e "s|\.[^.]*$||" <<< ${file} )
			asmbVers[${asmbVer}]=${asmbVer}
		done

		# Display assembly grammer versions ...
	    echo ""
		n=1
		for asmbVer in $( printf "%s\n" ${!asmbVers[@]}  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${asmbVer}
			((n++))
		done

		read -p "Assembly Version [$((n -1))]: " antlr_asmbVer_
		bashParmRead=true
	
		# Decode assembly grammer version selected ...
  		if [[ -z "${antlr_asmbVer_}" ]] ;  then antlr_asmbVer_=$((n -1)) ;  fi
		
   		n=1
		for asmbVer in $( printf "%s\n" ${!asmbVers[@]}  |  sort ) ;  do
		    if [[ ${antlr_asmbVer_} -eq ${n} ]] ;  then antlr_asmbVer=${asmbVer} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_dirsVer}" ]] ;  then

		# Build list of directive grammer versions...
		declare -A dirsVers
		for file in ${GrammerSource_DSpec}/_directives/* ;  do
			dirsVer=$( sed -e "s|${GrammerSource_DSpec}/_directives/Directives_||" -e "s|\.[^.]*$||" <<< ${file} )
			dirsVers[${dirsVer}]=${dirsVer}
		done

		# Display directive grammer versions ...
	    echo ""
		n=1
		for dirsVer in $( printf "%s\n" ${!dirsVers[@]}  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${dirsVer}
			((n++))
		done

		read -p "Directives Version [$((n -1))]: " antlr_dirsVer_
		bashParmRead=true
	
		# Decode directive grammer version selected ...
  		if [[ -z "${antlr_dirsVer_}" ]] ;  then antlr_dirsVer_=$((n -1)) ;  fi
		
	   	n=1
		for dirsVer in $( printf "%s\n" "${!dirsVers[@]}"  |  sort ) ;  do
		    if [[ ${antlr_dirsVer_} -eq ${n} ]] ;  then antlr_dirsVer=${dirsVer} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_exprVer}" ]] ;  then

		# Build list of expression grammer versions...
		declare -A exprVers
		for file in ${GrammerSource_DSpec}/_expressions/* ;  do
			exprVer=$( sed -e "s|${GrammerSource_DSpec}/_expressions/Expressions_||" -e "s|\.[^.]*$||" <<< ${file} )
			exprVers[${exprVer}]=${exprVer}
		done

		# Display expression grammer versions ...
	    echo ""
		n=1
		for exprVer in $( printf "%s\n" "${!exprVers[@]}"  |  sort ) ;  do
			printf "[%d] %s\n" ${n} ${exprVer}
			((n++))
		done

		read -p "Expressions Version [$((n -1))]: " antlr_exprVer_
		bashParmRead=true
	
		# Decode expression grammer version selected ...
  		if [[ -z "${antlr_exprVer_}" ]] ;  then antlr_exprVer_=$((n -1)) ;  fi
		
	   	n=1
		for exprVer in $( printf "%s\n" ${!exprVers[@]}  |  sort ) ;  do
		    if [[ ${antlr_exprVer_} -eq ${n} ]] ;  then antlr_exprVer=${exprVer} ;  fi
		    ((n++))
        done
	fi


	if [[ -n "${antlr_uPFam}" && -z "${antlr_cmd}" ]] ;  then
		
		antlr_cmd_Default="B"
			
		echo ""
		echo "[B] Build"
		echo "[C] Compile"
		echo "[D] Deploy"
		echo "[K] Clean"

		read -p "Command [${antlr_cmd_Default}]: " antlr_cmd_
		bashParmRead=true
		
		if [[ -z "${antlr_cmd_}" ]] ;  then antlr_cmd_=${antlr_cmd_Default} ;  fi
		if [[ "${antlr_cmd_^^}" == "B" ]] ;  then antlr_cmd="BUILD" ;  fi
		if [[ "${antlr_cmd_^^}" == "C" ]] ;  then antlr_cmd="COMPILE" ;  fi
		if [[ "${antlr_cmd_^^}" == "D" ]] ;  then antlr_cmd="DEPLOY" ;  fi
		if [[ "${antlr_cmd_^^}" == "K" ]] ;  then antlr_cmd="CLEAN" ;  fi
	fi


	if [[ -n "${antlr_uPFam}" && -n "${antlr_cmd}" ]] ;  then

		echo ""
		echo "       uP Family: ${antlr_uPFam}"
		echo "              uP: ${antlr_uP}"
		echo "          uP Ver: ${antlr_uPVer}"
		echo "    Assembly Ver: ${antlr_asmbVer}"
		echo "  Directives Ver: ${antlr_dirsVer}"
		echo " Expressions Ver: ${antlr_exprVer}"
		echo "         Command: ${antlr_cmd}"
	fi

	asmbHeaderFSpec=_headers/Assembly_Header.g4
	uPHeaderFSpec=_headers/Processor_Header.g4

	echo ""
	echo ">>> asmbHeaderFSpec ${GrammerSource_DSpec}/${asmbHeaderFSpec}"
	echo ">>> uPHeaderFSpec ${GrammerSource_DSpec}/${uPHeaderFSpec}"

	asmbSourceFSpec=_assemblies/Assembly_${antlr_asmbVer}.g4
	dirsSourceFSpec=_directives/Directives_${antlr_dirsVer}.g4
	exprSourceFSpec=_expressions/Expressions_${antlr_exprVer}.g4
	uPSourceFSpec=processors/_${antlr_uPFam}/${antlr_uP}_${antlr_uPVer}.g4

	echo ""
	echo ">>> asmbSourceFSpec ${GrammerSource_DSpec}/${asmbSourceFSpec}"
	echo ">>> dirsSourceFSpec ${GrammerSource_DSpec}/${dirsSourceFSpec}"
	echo ">>> exprSourceFSpec ${GrammerSource_DSpec}/${exprSourceFSpec}"
	echo ">>> uPSourceFSpec ${GrammerSource_DSpec}/${uPSourceFSpec}"


	echo ""

    rm  -rf ${GrammerWorkspaceDSpec}/*

	##asmbTargetFSpec=${GrammerSource_DSpec}/_assemblies/Assembly_${antlr_asmbVer}.g4
	##asmbTargetFSpec=${GrammerWorkspaceDSpec}/Assembly_${antlr_asmbVer}.g4
	asmbTargetFSpec=Assembly.g4
	echo ">>> asmbTargetFSpec ${GrammerWorkspaceDSpec}/${asmbTargetFSpec}"
	



	cat  ${GrammerSource_DSpec}/${asmbHeaderFSpec}  >  ${GrammerWorkspaceDSpec}/${asmbTargetFSpec}
	cat  ${GrammerSource_DSpec}/${dirsSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${asmbTargetFSpec}
	cat  ${GrammerSource_DSpec}/${exprSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${asmbTargetFSpec}



	##uPTargetFSpec=${GrammerSource_DSpec}/processors/_${antlr_uPFam}/${antlr_uP}_${antlr_uPVer}.g4
	##uPTargetFSpec=${GrammerWorkspaceDSpec}/${antlr_uP}_${antlr_uPVer}.g4
	uPTargetFSpec=${antlr_uP}_.g4
	echo ">>> uPTargetFSpec ${GrammerWorkspaceDSpec}/${uPTargetFSpec}"

	cat  ${GrammerSource_DSpec}/${uPHeaderFSpec}  >  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}
	cat  ${GrammerSource_DSpec}/${asmbSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}
	cat  ${GrammerSource_DSpec}/${uPSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}
	cat  ${GrammerSource_DSpec}/${dirsSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}
	cat  ${GrammerSource_DSpec}/${exprSourceFSpec}  >>  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}

	sed  -i "s|\${uP}|${antlr_uP}|"  ${GrammerWorkspaceDSpec}/${uPTargetFSpec}



	##cp  ${asmbTargetFSpec}  ${GrammerWorkspaceDSpec}/Assembly.g4
	java  -cp ${ANTLRTool_FSpec}  org.antlr.v4.Tool  -o "${GrammerWorkspaceDSpec}/xcom/retro/xa/antlr"  -package xcom.retro.xa.antlr  ${GrammerWorkspaceDSpec}/Assembly.g4

	##cp  ${uPTargetFSpec}  ${GrammerWorkspaceDSpec}/${antlr_uP}_.g4
    java  -cp ${ANTLRTool_FSpec}  org.antlr.v4.Tool  -o "${GrammerWorkspaceDSpec}/xcom/retro/xa/antlr/processors/${antlr_uP}"  -package xcom.retro.xa.antlr.processors.${antlr_uP}  ${GrammerWorkspaceDSpec}/${antlr_uP}_.g4
