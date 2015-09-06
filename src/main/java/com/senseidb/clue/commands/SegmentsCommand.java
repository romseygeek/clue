package com.senseidb.clue.commands;
/*
 *   Copyright (c) 2015 Lemur Consulting Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import java.io.PrintStream;

import com.senseidb.clue.ClueContext;
import org.apache.lucene.index.SegmentCommitInfo;
import org.apache.lucene.index.SegmentInfos;

public class SegmentsCommand extends ClueCommand {

    public SegmentsCommand(ClueContext ctx) {
        super(ctx);
    }

    @Override
    public String getName() {
        return "segments";
    }

    @Override
    public String help() {
        return "prints details of segments in the index";
    }

    @Override
    public void execute(String[] args, PrintStream out) throws Exception {

        SegmentInfos sis = new SegmentInfos();
        sis.read(ctx.getDirectory());

        for (SegmentCommitInfo sci : sis) {
            out.print(sci.info.toString());
            out.print("  ");
            out.print(sci.info.getDocCount());
            out.print(" (");
            out.print(sci.getDelCount());
            out.println(")");
        }

    }
}
